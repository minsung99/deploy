import React from 'react';
import { useNavigate } from 'react-router-dom';
import './footer.css';

function Footer() {
  const navigate = useNavigate();

  return (
    <div className="footer">
      <div className="icon-container">
        <img
          src="/swipe_icon.png"
          alt="Swipe Icon"
          className="footer-icon"
          onClick={() => navigate('/swipe')}
        />
        <img
          src="/search_icon.png"
          alt="Search Icon"
          className="footer-icon"
          onClick={() => navigate('/openchat')}
        />
        <img
          src="/chat_icon.png"
          alt="Chat Icon"
          className="footer-icon"
          onClick={() => navigate('/chatroom')}
        />
      </div>
    </div>
  );
}

export default Footer;
