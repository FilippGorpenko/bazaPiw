CREATE TABLE public.deposit
(
  id             BIGSERIAL                   NOT NULL,
  currency           CHARACTER VARYING(255)      NOT NULL,
  amount           CHARACTER VARYING(255)   NOT NULL,
  deposit_address           CHARACTER VARYING(255)   NOT NULL,
  status           CHARACTER VARYING(255)   NOT NULL,
  user_id           BIGINT   NOT NULL,
  creation_time  TIMESTAMP WITHOUT TIME ZONE NOT NULL,

  CONSTRAINT deposit_pkey PRIMARY KEY (id)
)
WITH (
OIDS = FALSE
);
ALTER TABLE public.deposit
  OWNER TO crm_role;