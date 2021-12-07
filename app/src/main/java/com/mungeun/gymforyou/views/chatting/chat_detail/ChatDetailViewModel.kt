package com.mungeun.gymforyou.views.chatting.chat_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.mungeun.gymforyou.utilities.preference.PreferenceManger
import dagger.hilt.android.lifecycle.HiltViewModel
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import javax.inject.Inject

// 테스트용으로 주입
data class Message(
    val userName: String,
    val messageContent: String,
    val roomName: String,
    var viewType: Int
)

data class initialData(val userName: String, val roomName: String)
data class SendMessage(val userName: String, val messageContent: String, val roomName: String)

enum class MessageType(val index: Int) {
    CHAT_MINE(0), CHAT_PARTNER(1), USER_JOIN(2), USER_LEAVE(3);
}

@HiltViewModel
class ChatDetailViewModel @Inject constructor(private val preferenceManger: PreferenceManger): ViewModel() {

    private var mSocket: Socket = IO.socket("http://146.56.152.117:3001")
    private val userName :Lazy<String> = lazy { preferenceManger.userName }
    private var roomName: String = "b"

    val gson: Gson = Gson()

    var editTextSend = MutableLiveData<String>("")
    var message = MutableLiveData<MutableList<Message>>()
    var temp = ArrayList<Message>()


    var onConnect = Emitter.Listener {
        val data = initialData(userName.value, roomName)
        val jsonData = gson.toJson(data)
        mSocket.emit("subscribe", jsonData)

    }

    var onUserLeft = Emitter.Listener {
        val leftUserName = it[0] as String
        val chat: Message = Message(leftUserName, "", "", MessageType.USER_LEAVE.index)
        temp.add(chat)
        message.postValue(temp)
    }

    var onUpdateChat = Emitter.Listener {
        val chat: Message = gson.fromJson(it[0].toString(), Message::class.java)
        chat.viewType = MessageType.CHAT_PARTNER.index
        temp.add(chat)
        message.postValue(temp)
        //addItemToRecyclerView(chat)
    }

    var onNewUser = Emitter.Listener {
        val name = it[0] as String //This pass the userName!
        val chat = Message(name, "", roomName, MessageType.USER_JOIN.index)
        temp.add(chat)
        message.postValue(temp)
        //addItemToRecyclerView(chat)
        //Log.d(TAG, "on New User triggered.")
    }


    init {
        mSocket.connect()
        //  Log.d("success", mSocket.id())
        mSocket.on(Socket.EVENT_CONNECT, onConnect);
        mSocket.on("newUserToChatRoom", onNewUser)
        mSocket.on("updateChat", onUpdateChat)
        mSocket.on("userLeftChatRoom", onUserLeft)
    }


    fun sendMessage() {
        val content = editTextSend.value.toString().trim()
        val sendData = SendMessage(userName.value, content, roomName)
        val jsonData = gson.toJson(sendData)
        mSocket.emit("newMessage", jsonData)
        temp.add(Message(userName.value, content, roomName, MessageType.CHAT_MINE.index))
        message.postValue(temp)
        editTextSend.value = ""

    }

}