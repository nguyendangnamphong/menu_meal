CREATE TABLE meal (
    id VARCHAR(50) PRIMARY KEY,
    meal_id VARCHAR(50),
    dishes TEXT[], -- Array of dish IDs
    date DATE NOT NULL,
    meal_time VARCHAR(20) NOT NULL
);

CREATE TABLE meal_now (
    meal_id VARCHAR(50) PRIMARY KEY,
    dishes TEXT[], -- Array of dish IDs
    date DATE NOT NULL,
    meal_time VARCHAR(20) NOT NULL
);

CREATE TABLE parent_eat (
    user_id VARCHAR(50),
    meal_id VARCHAR(50),
    date DATE NOT NULL,
    meal_time VARCHAR(20) NOT NULL,
    PRIMARY KEY (user_id, meal_id)
);

CREATE TABLE dish_cache (
    id SERIAL PRIMARY KEY,
    dish_id VARCHAR(50),
    name VARCHAR(100),
    type VARCHAR(20),
    time_not_repeat DECIMAL,
    price DECIMAL
);

CREATE INDEX idx_meal_date ON meal(date);