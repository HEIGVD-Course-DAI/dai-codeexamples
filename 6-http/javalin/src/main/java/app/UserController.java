package app;

import io.javalin.http.Context;
import java.util.concurrent.ConcurrentHashMap;

class UserController {
    
    // "Database" of users
    // Since the server is multi-threaded, we need to use a thread-safe data structure
    // such as ConcurrentHashMap or HashMap
    private ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<Integer, User>();
    private int lastId = 0;

    public UserController() {
        // Add some users to the "database"
        users.put(++lastId, new User("Anita", "Braig"));
        users.put(++lastId, new User("Bill", "Ding"));
        users.put(++lastId, new User("Chris P.", "Bacon"));
    }

    public void getOne(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        ctx.json(users.get(id));
    }

    public void getAll(Context ctx) {
        ctx.json(users);
    }

    public void create(Context ctx) {
        User user = ctx.bodyAsClass(User.class);
        users.put(++lastId, user);
        ctx.status(201);
    }

    public void delete(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        users.remove(id);
        ctx.status(204);
    }

    public void update(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        User user = ctx.bodyAsClass(User.class);
        users.put(id, user);
        ctx.status(200);
    }

}
