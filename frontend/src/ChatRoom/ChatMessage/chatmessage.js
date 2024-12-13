import React from "react";

import AccountCircleOutlinedIcon from "@mui/icons-material/AccountCircleOutlined";

import "./chatmessage.css";

const MessageType = {
    CHAT: "CHAT",
    JOIN: "JOIN",
    LEAVE: "LEAVE"
};

const ChatMessage = ({ msg, isSender }) => {
    const isChat = (msg.type == MessageType.CHAT);

    return (
        <div className={`chat-message-container ${isSender ? "sender" : "receiver"}`} key={msg.user.id}>
            {!isSender && isChat &&
                <div className="avatar-message">
                    <AccountCircleOutlinedIcon sx={{ fontSize: 40, color: "#4caf50" }} />
                </div>}
            <div className="message-content">
                {!isSender && isChat && <div className="username">{msg.user.name}</div>}
                <div className={`${isChat ? "chat-message" : "event-message"}`}>
                    {msg.detail}
                    {isChat && <div className="message-time">{new Date(msg.senttime).toLocaleTimeString([], { hour: "2-digit", minute: "2-digit" })}</div>}
                </div>
            </div>
        </div>
    );
};

export default ChatMessage;