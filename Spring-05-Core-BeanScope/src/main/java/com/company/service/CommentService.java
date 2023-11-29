package com.company.service;
import com.company.model.Comment;
import com.company.proxy.CommentNotificationProxy;
import com.company.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/*
 * 🦋 Bean Scopes
 * · Spring has multiple different approaches for creating beans and managing their lifecycle. These approaches are called “scopes”.
 * · The scope of beans defines how the framework manages the object instances.
 * · Spring bean scopes: · Singleton · Prototype · Request · Session etc.
 * 🔺 Singleton Scope:
 * · Singleton is default scope in Spring. It called singleton, because you always get the same instance when you refer to a specific bean.
 * 🔺 Prototype Scope:
 * · It is called prototype, because every time you request a reference to a prototype-scoped bean, Spring creates a new object's instance.
 * · For prototype beans, Spring does not create and manage an object's instance directly. The framework manages the object’s type
 *   and creates a new instance every time someone requests a reference to the bean.
 * · We should use @Scope() annotation to change the bean’s scope in class level. A new instance will be created each time we refer to it.
 * · When we call beans more than one time and want to get a different object each time, we can use @Scope("prototype") annotation in respective class level.
 *
 * 🦋 Eager and Lazy Instantiation
 * · When a container initializes (created), ALL Singleton BEANS are created automatically. It is DEFAULT behavior and called “eager instantiation”.
 * · We can use @Lazy annotation to prevent that. @Lazy will prevent creation of the beans until we refer to them.
 *
 * ❗️...
 * · Singletons (default scope) are created once per container (on start up for a webapp, for example).
 *   Prototypes are created once per injection (every time a class gets the bean).
 * · @Lazy tells Spring to not eagerly create the bean. For singletons, that means that it will not be created on startup,
 *   but instead will be but instead will be constructed once it is injected for the first time. It has no effects on prototype,
 *   since they aren't created until they're injected already.
 * · In summary, @Lazy only applies to singletons. If said lazy singleton is injected by a non-lazy singleton (which gets created on start up),
 *   then the @Lazy does nothing effectively.
 *
 */
@Scope("prototype")
//@Scope(BeanDefinition.SCOPE_PROTOTYPE)
//@Lazy
@Component
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentNotificationProxy commentNotificationProxy;

    @Autowired
    public CommentService(CommentRepository commentRepository, @Qualifier("PUSH") CommentNotificationProxy commentNotificationProxy) {
        this.commentRepository = commentRepository;
        this.commentNotificationProxy = commentNotificationProxy;
        System.out.println("Hello"); // Test: When a container initializes, we will see "Hello" on the console if the @Lazy annotation is not used.
                                     // @Lazy only applies to singletons
    }

    public void publishComment(Comment comment){
        commentRepository.storeComment(comment);
        commentNotificationProxy.sendComment(comment);
    }
}
