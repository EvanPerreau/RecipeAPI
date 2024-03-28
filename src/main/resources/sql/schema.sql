CREATE TYPE DifficultyLevel AS ENUM(
    'easy',
    'medium',
    'hard',
    'expert'
);

CREATE TABLE Recipe(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    instructions TEXT NOT NULL,
    preparation_time INT NOT NULL,
    cooking_time INT NOT NULL,
    difficulty_level DifficultyLevel NOT NULL,
    servings INT NOT NULL
);

CREATE TABLE Ingredient(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE Category(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE RecipeIngredient(
    recipe_id INT REFERENCES Recipe(id) ON DELETE CASCADE ON UPDATE CASCADE,
    ingredient_id INT REFERENCES Ingredient(id) ON DELETE CASCADE ON UPDATE CASCADE,
    quantity VARCHAR(255) NOT NULL,
    PRIMARY KEY(recipe_id, ingredient_id)
);