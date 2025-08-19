CREATE TABLE IF NOT EXISTS work_orders(
  id UUID PRIMARY KEY,
  order_id VARCHAR(64) NOT NULL,
  status VARCHAR(32) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT now()
);
CREATE TABLE IF NOT EXISTS work_order_lines(
  id BIGSERIAL PRIMARY KEY,
  work_order_id UUID REFERENCES work_orders(id) ON DELETE CASCADE,
  sku VARCHAR(64) NOT NULL,
  quantity INT NOT NULL CHECK (quantity>0)
);
