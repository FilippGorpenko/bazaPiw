CREATE TABLE public.order
(
  id             BIGSERIAL                   NOT NULL,
  status           CHARACTER VARYING(255)   NOT NULL,
  user_id           BIGINT   NOT NULL,
  product_id           BIGINT   NOT NULL,
  creation_time  TIMESTAMP WITHOUT TIME ZONE NOT NULL,

  CONSTRAINT order_pkey PRIMARY KEY (id)
)
WITH (
OIDS = FALSE
);
ALTER TABLE public.order
  OWNER TO crm_role;

