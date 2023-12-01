package app;

import io.javalin.*;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7000);

        UserController userController = new UserController();
        app.get("/api/users", userController::getAll);
        app.get("/api/users/{id}", userController::getOne);
        app.post("/api/users/", userController::create);
        app.put("/api/users/{id}", userController::update);
        app.delete("/api/users/{id}", userController::delete);
    }
}