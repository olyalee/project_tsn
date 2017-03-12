package com.teachers_social_network.web.servlet;

import com.teachers_social_network.model.Message;
import com.teachers_social_network.model.User;
import com.teachers_social_network.service.interfaces.MessageService;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Olya Lee on 11.03.2017.
 */
@Singleton
public class MessagesServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(MessagesServlet.class);

    private final MessageService messageService;

    @Inject
    public MessagesServlet(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession(false);
        final User user = (User) session.getAttribute("user");
        String login = user.getLogin();

        if (req.getParameter("send") != null) {
            Message message = Message.builder()
                    .id(1)
                    .from_user(user.getLogin())
                    .to_user(req.getParameter("toUser"))
                    .text(req.getParameter("messageText"))
                    .time(new Timestamp(System.currentTimeMillis())).build();

            if (messageService.create(message)) {
                req.setAttribute("wasSent", "true");
            } else {
                req.setAttribute("wasSent", "false");
            }

        }

        List<Message> messages = messageService.getByFromUser(login);
        messages.addAll(messageService.getByToUser(login));
        session.setAttribute("messagesList", messages);

        req.getRequestDispatcher("/WEB-INF/jsp/messages.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        final HttpSession session = req.getSession(false);
        final User user = (User) session.getAttribute("user");
        String login = user.getLogin();

        if (req.getParameter("send") != null) {
            Message message = Message.builder()
                    .id(1)
                    .from_user(user.getLogin())
                    .to_user(req.getParameter("toUser"))
                    .text(req.getParameter("messageText"))
                    .time(new Timestamp(System.currentTimeMillis())).build();


            logger.info(req.getParameter("messageText"));

            if (messageService.create(message)) {
                req.setAttribute("wasSent", "true");
            } else {
                req.setAttribute("wasSent", "false");
            }

        }

        List<Message> messages = messageService.getByFromUser(login);
        messages.addAll(messageService.getByToUser(login));
        for(int i=0; i<messages.size(); i++){
            logger.info(messages.get(i).getText());
        }
        session.setAttribute("messagesList", messages);

        req.getRequestDispatcher("/WEB-INF/jsp/messages.jsp").forward(req, resp);
    }
}
