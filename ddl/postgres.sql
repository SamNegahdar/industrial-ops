-- Databases (create per service)
-- CREATE DATABASE orderservice;
-- CREATE DATABASE inventoryservice;

-- Order-service tables (also via Flyway):
CREATE TABLE IF NOT EXISTS orders(
  id UUID PRIMARY KEY,
  customer_id VARCHAR(64) NOT NULL,
  status VARCHAR(32) NOT NULL,
  created_at TIMESTAMP NOT NULL
);
CREATE TABLE IF NOT EXISTS order_lines(
  id BIGSERIAL PRIMARY KEY,
  order_id UUID REFERENCES orders(id) ON DELETE CASCADE,
  sku VARCHAR(64) NOT NULL,
  quantity INT NOT NULL CHECK (quantity>0),
  unit_price NUMERIC(18,2) NOT NULL CHECK (unit_price>=0)
);

-- Inventory tables:
CREATE TABLE IF NOT EXISTS stock(
  sku VARCHAR(64) PRIMARY KEY,
  on_hand INT NOT NULL CHECK (on_hand>=0)
);
