CREATE TABLE public.product
(
  id             BIGSERIAL                   NOT NULL,
  name           CHARACTER VARYING(255)      NOT NULL,
  description           CHARACTER VARYING(255)   ,
  value                    NUMERIC (19,2) NOT NULL,
  on_sale     BOOLEAN                     NOT NULL,
  CONSTRAINT product_pkey PRIMARY KEY (id)
)
WITH (
OIDS = FALSE
);
ALTER TABLE public.product
  OWNER TO crm_role;
