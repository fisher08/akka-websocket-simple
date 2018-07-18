package controllers;

import akka.stream.javadsl.*;
import play.mvc.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(views.html.index.render());
    }

    public WebSocket socket() {
        return WebSocket.Text.accept(request -> {
            // Log events to the console
            Sink<String, ?> in = Sink.foreach(System.out::println);

            // Send a single 'Hello!' message and then leave the socket open
            Source<String, ?> out = Source.single("Hello!").concat(Source.maybe());

            return Flow.fromSinkAndSource(in, out);
        });
    }

}
