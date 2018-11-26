CREATE TABLE public.user
(
  id             BIGSERIAL                   NOT NULL,
  username           CHARACTER VARYING(255)      NOT NULL,
  password           CHARACTER VARYING(255)      NOT NULL,
  is_deleted     BOOLEAN                     NOT NULL,
  version        BIGINT                      NOT NULL DEFAULT 0,
  CONSTRAINT user_pkey PRIMARY KEY (id)
)
WITH (
OIDS = FALSE
);
ALTER TABLE public.user
  OWNER TO crm_role;
