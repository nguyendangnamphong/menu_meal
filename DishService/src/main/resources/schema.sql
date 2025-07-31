CREATE TABLE Dish (
    id INTEGER PRIMARY KEY CHECK (id >= 100 AND id <= 999),
    name VARCHAR(255) NOT NULL UNIQUE,
    dish_type VARCHAR(10) NOT NULL CHECK (dish_type IN ('đạm', 'rau')),
    time_not_repeat DECIMAL(10,2) NOT NULL CHECK (time_not_repeat >= 0 AND time_not_repeat <= 7),
    price DECIMAL(10,2) NOT NULL
);