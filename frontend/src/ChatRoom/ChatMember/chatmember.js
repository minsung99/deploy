import React from "react";
import "./chatmember.css";

const ChatMember = ({ member }) => {
    const onClickMember = () => {
        // 멤버 클릭 시, 실행되는 코드
        console.log(`Member${member.id} Clicked!`);
    };
    
    return (
        <div className="member-item" key={member.id} onClick={onClickMember}>
            <div className="avatar">{member.avatar}</div>
            <div className="member-name">{member.name}</div>
        </div>
    );
};

export default ChatMember;