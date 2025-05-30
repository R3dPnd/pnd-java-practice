-- Drop tables if they exist
DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS categories;

-- Create categories table
CREATE TABLE categories (
    category_id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create products table
CREATE TABLE products (
    product_id SERIAL PRIMARY KEY,
    category_id INTEGER REFERENCES categories(category_id),
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    stock_quantity INTEGER NOT NULL DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create customers table
CREATE TABLE customers (
    customer_id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20),
    address TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create orders table
CREATE TABLE orders (
    order_id SERIAL PRIMARY KEY,
    customer_id INTEGER REFERENCES customers(customer_id),
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(10,2) NOT NULL DEFAULT 0,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    shipping_address TEXT,
    CONSTRAINT valid_status CHECK (status IN ('PENDING', 'PROCESSING', 'SHIPPED', 'DELIVERED', 'CANCELLED'))
);

-- Create order_items table
CREATE TABLE order_items (
    order_item_id SERIAL PRIMARY KEY,
    order_id INTEGER REFERENCES orders(order_id),
    product_id INTEGER REFERENCES products(product_id),
    quantity INTEGER NOT NULL,
    unit_price DECIMAL(10,2) NOT NULL,
    CONSTRAINT positive_quantity CHECK (quantity > 0)
);

-- Create indexes for better performance
CREATE INDEX idx_products_category ON products(category_id);
CREATE INDEX idx_orders_customer ON orders(customer_id);
CREATE INDEX idx_order_items_order ON order_items(order_id);
CREATE INDEX idx_order_items_product ON order_items(product_id);

-- Insert sample data into categories
INSERT INTO categories (name, description) VALUES
    ('Electronics', 'Electronic devices and accessories'),
    ('Books', 'Physical and digital books'),
    ('Clothing', 'Apparel and accessories'),
    ('Home & Garden', 'Home improvement and garden supplies');

-- Insert sample products
INSERT INTO products (category_id, name, description, price, stock_quantity) VALUES
    (1, 'Smartphone X', 'Latest smartphone with advanced features', 699.99, 50),
    (1, 'Laptop Pro', 'Professional laptop for developers', 1299.99, 30),
    (2, 'Java Programming', 'Complete guide to Java programming', 49.99, 100),
    (2, 'Spring Boot in Action', 'Learn Spring Boot development', 39.99, 75),
    (3, 'Developer T-Shirt', 'Comfortable cotton t-shirt for developers', 19.99, 200),
    (3, 'Coding Hoodie', 'Warm hoodie with programmer design', 49.99, 100),
    (4, 'Ergonomic Chair', 'Comfortable chair for long coding sessions', 299.99, 25),
    (4, 'LED Desk Lamp', 'Adjustable LED lamp for your desk', 79.99, 40);

-- Insert sample customers
INSERT INTO customers (first_name, last_name, email, phone, address) VALUES
    ('John', 'Doe', 'john.doe@email.com', '123-456-7890', '123 Main St, City'),
    ('Jane', 'Smith', 'jane.smith@email.com', '234-567-8901', '456 Oak Ave, Town'),
    ('Bob', 'Johnson', 'bob.johnson@email.com', '345-678-9012', '789 Pine Rd, Village'),
    ('Alice', 'Brown', 'alice.brown@email.com', '456-789-0123', '321 Elm St, Borough');

-- Insert sample orders
INSERT INTO orders (customer_id, total_amount, status, shipping_address) VALUES
    (1, 749.98, 'DELIVERED', '123 Main St, City'),
    (2, 1339.98, 'SHIPPED', '456 Oak Ave, Town'),
    (3, 89.98, 'PROCESSING', '789 Pine Rd, Village'),
    (4, 379.98, 'PENDING', '321 Elm St, Borough');

-- Insert sample order items
INSERT INTO order_items (order_id, product_id, quantity, unit_price) VALUES
    (1, 1, 1, 699.99),
    (1, 3, 1, 49.99),
    (2, 2, 1, 1299.99),
    (2, 4, 1, 39.99),
    (3, 3, 1, 49.99),
    (3, 4, 1, 39.99),
    (4, 7, 1, 299.99),
    (4, 8, 1, 79.99);

-- Create a view for order summaries
CREATE OR REPLACE VIEW order_summaries AS
SELECT 
    o.order_id,
    c.first_name || ' ' || c.last_name as customer_name,
    o.order_date,
    o.status,
    COUNT(oi.order_item_id) as total_items,
    o.total_amount
FROM orders o
JOIN customers c ON o.customer_id = c.customer_id
JOIN order_items oi ON o.order_id = oi.order_id
GROUP BY o.order_id, c.first_name, c.last_name, o.order_date, o.status, o.total_amount;

-- Create a function to calculate total revenue
CREATE OR REPLACE FUNCTION calculate_total_revenue()
RETURNS DECIMAL(10,2) AS $$
BEGIN
    RETURN (SELECT SUM(total_amount) FROM orders WHERE status != 'CANCELLED');
END;
$$ LANGUAGE plpgsql;

-- Create a trigger to update order total amount
CREATE OR REPLACE FUNCTION update_order_total()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE orders
    SET total_amount = (
        SELECT SUM(quantity * unit_price)
        FROM order_items
        WHERE order_id = NEW.order_id
    )
    WHERE order_id = NEW.order_id;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_order_total_trigger
AFTER INSERT OR UPDATE OR DELETE ON order_items
FOR EACH ROW
EXECUTE FUNCTION update_order_total();