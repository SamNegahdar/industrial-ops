CREATE TABLE IF NOT EXISTS shipments(
  id UUID PRIMARY KEY,
  order_id VARCHAR(64) NOT NULL,
  carrier VARCHAR(64),
  tracking VARCHAR(64),
  status VARCHAR(16) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT now()
);
