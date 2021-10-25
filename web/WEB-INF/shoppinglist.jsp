<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        Hello, ${username} <a href="ShoppingList?action=logout">Logout</a>
        <h2>List</h2>
        <form action="" method="POST">
            Add item: <input type="text" name="item"><input type="submit" value="Add">
            <input type="hidden" name="action" value="add">
        </form>

        <form action="" method="POST">
            <ul>
                ${items}
            </ul>
            <input type="submit" value="Delete">
            <input type="hidden" name="action" value="delete">
        </form>
    </body>
</html>
