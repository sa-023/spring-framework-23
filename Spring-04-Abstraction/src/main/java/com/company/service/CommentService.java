package com.company.service;

import com.company.model.Comment;
import com.company.proxy.CommentNotificationProxy;
import com.company.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/*
 * 🦋 Package structure:
 * · Service: Refers to the objects implementing use cases (Business Logic).
 * · Repository: Refers to the objects working directly with a database.
 * · Proxy: Refers to the objects whose responsibilities are to establish communication with something outside the app.
 * · Model/Entity: Refers to objects (POJOs) that model the data the app uses.
 *
 * 🖍️...
 * · Interfaces are abstract so, we never use @Component annotation with interfaces.
 * · POJO classes not get @Component annotation because we don't use it as dependencies.
 * · We need to add the object to the Spring container IF IT EITHER HAS A DEPENDENCY or IF IT IS A DEPENDENCY ITSELF.
 * · We add interfaces name as dependencies to the classes. Classes have implementation and might change in the future, but interfaces will not.
 * · It is good practice to declare dependencies as private final because they do not change, and we create a constructor right away, and initialize our dependencies.
 *
 * ❗️An Interface might have two or more implementations for different service usages, but Spring will always inject one of them.
 *   We have to specify which implementation we want to use. If we don't specify it, Spring will throw an error.
 *   Ex: CommentPushNotificationProxy & EmailCommentNotificationProxy classes are implemented from CommentNotificationProxy interface.
 * · Below are the ways to specify the implementation for Spring:
 *   1. Use the @Primary annotation in class level to mark one of them as default.
 *   2. Use the @Qualifier annotation with the name of the bean and then instruct Spring to inject that bean by name.
 *      2.1. Use @Qualifier("defaultBeanName") annotation in the constructor with the default Bean Name. Ex: @Qualifier("commentPushNotificationProxy").
 *      2.2. Use @Qualifier("customBeanName") annotation in class level and in the constructor by creating custom bean name. Ex: @Qualifier("EMAIL").
 */
@Component
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentNotificationProxy commentNotificationProxy;

    @Autowired
    public CommentService(CommentRepository commentRepository, @Qualifier("PUSH") CommentNotificationProxy commentNotificationProxy) {
        this.commentRepository = commentRepository;
        this.commentNotificationProxy = commentNotificationProxy;
    }

    public void publishComment(Comment comment){
        commentRepository.storeComment(comment);
        commentNotificationProxy.sendComment(comment);
    }
}
