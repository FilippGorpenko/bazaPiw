CREATE TABLE cityfit_trainer.beer
(
  id           BIGSERIAL              NOT NULL,
  name         CHARACTER VARYING(255) NOT NULL,
  tagline      CHARACTER VARYING(255) NOT NULL,
  first_brewed CHARACTER VARYING(255) NOT NULL,
  image_url    CHARACTER VARYING(255) NOT NULL,
  ibu          BIGINT,
  description  TEXT                   NOT NULL,
  food_pairing TEXT,
  CONSTRAINT beer_pkey PRIMARY KEY (id)
)
WITH (
OIDS = FALSE
);
ALTER TABLE cityfit_trainer.beer
  OWNER TO crm_role;
