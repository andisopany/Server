/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.Date;
import net.sf.json.JSONObject;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class ClientMessageHandler extends IoHandlerAdapter
{
    @Override
    public void exceptionCaught( IoSession session, Throwable cause ) throws Exception
    {
        cause.printStackTrace();
    }

    @Override
    public void messageReceived( IoSession session, Object message ) throws Exception
    {
        String str = message.toString();
        JSONObject messageObject = JSONObject.fromObject(str);
        session.write(messageObject.get("type"));
        session.write(messageObject.get("name"));
    }

    @Override
    public void sessionIdle( IoSession session, IdleStatus status ) throws Exception
    {
        System.out.println( "IDLE " + session.getIdleCount( status ));
    }

    @Override
    public void sessionCreated( IoSession session) throws Exception {
        
        System.out.println( "Connect " + session.getRemoteAddress());
        session.setAttribute("context",this);
    }
    
    @Override
    public void sessionClosed( IoSession session) throws Exception
    {
        System.out.println( "Closed  " + session.getRemoteAddress());
    }

}
