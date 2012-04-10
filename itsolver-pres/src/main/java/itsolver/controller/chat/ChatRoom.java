/* ChatRoom.java

 {{IS_NOTE
 Purpose:
 
 Description:
 
 History:
 Aug 17, 2007 12:58:55 PM , Created by robbiecheng
 }}IS_NOTE

 Copyright (C) 2007 Potix Corporation. All Rights Reserved.

 {{IS_RIGHT
 This program is distributed under GPL Version 2.0 in the hope that
 it will be useful, but WITHOUT ANY WARRANTY.
 }}IS_RIGHT
 */

package itsolver.controller.chat;

import java.util.Collection;
import java.util.LinkedList;

/**
 * 
 * @author robbiecheng
 */
public class ChatRoom {
	private Collection<Chatter> _chatters;

	private static final String SIGNAL = "~~~";

	public ChatRoom() {
		_chatters = new LinkedList<Chatter>();
	}

	/**
	 * broadcast messages to all chatters except sender
	 * 
	 * @param sender
	 * @param message
	 */
	public void broadcast(String sender, String message) {
		say(sender, sender + ":" + message);
	}

	private void say(String sender, String message) {
		synchronized (_chatters) {
			for (Chatter _chatter : _chatters)
				if (!_chatter.getSender().equals(sender))
					_chatter.addMessage(message);
		}
	}

	/**
	 * subscribte to the chatroom
	 * 
	 * @param chatter
	 */

	public void subscribe(Chatter chatter) {
		chatter.addMessage(SIGNAL + "Welcome " + chatter.getSender() + SIGNAL);
		synchronized (_chatters) {
			_chatters.add(chatter);
		}
		say(chatter.getSender(), SIGNAL + chatter.getSender()
				+ " join this chatroom" + SIGNAL);
	}

	/**
	 * unsubsctibe to the chatroom
	 * 
	 * @param chatter
	 */
	public void unsubscribe(Chatter chatter) {
		_chatters.remove(chatter);
		chatter.addMessage(SIGNAL + "Bye " + chatter.getSender() + SIGNAL);
		synchronized (_chatters) {
			for (Chatter _chatter : _chatters)
				_chatter.addMessage(SIGNAL + chatter.getSender()
						+ " leaves the chat room!" + SIGNAL);
		}
	}
}
