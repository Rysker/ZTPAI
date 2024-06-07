--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2
-- Dumped by pg_dump version 16.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: account_type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.account_type (
    account_type_id integer NOT NULL,
    name character varying(100)
);


ALTER TABLE public.account_type OWNER TO postgres;

--
-- Name: account_type_account_type_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.account_type_account_type_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.account_type_account_type_id_seq OWNER TO postgres;

--
-- Name: account_type_account_type_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.account_type_account_type_id_seq OWNED BY public.account_type.account_type_id;


--
-- Name: attribute; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.attribute (
    attribute_id integer NOT NULL,
    name character varying(100) NOT NULL
);


ALTER TABLE public.attribute OWNER TO postgres;

--
-- Name: attribute_attribute_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.attribute_attribute_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.attribute_attribute_id_seq OWNER TO postgres;

--
-- Name: attribute_attribute_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.attribute_attribute_id_seq OWNED BY public.attribute.attribute_id;


--
-- Name: collectible; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.collectible (
    collectible_id integer NOT NULL,
    model_kit_id integer NOT NULL,
    progress_id integer NOT NULL,
    is_public boolean DEFAULT false NOT NULL,
    completion_date date,
    list_order integer,
    collection_id integer NOT NULL
);


ALTER TABLE public.collectible OWNER TO postgres;

--
-- Name: collectible_collectible_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.collectible_collectible_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.collectible_collectible_id_seq OWNER TO postgres;

--
-- Name: collectible_collectible_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.collectible_collectible_id_seq OWNED BY public.collectible.collectible_id;


--
-- Name: collection; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.collection (
    collection_id integer NOT NULL,
    user_id integer NOT NULL
);


ALTER TABLE public.collection OWNER TO postgres;

--
-- Name: collection_collection_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.collection_collection_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.collection_collection_id_seq OWNER TO postgres;

--
-- Name: collection_collection_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.collection_collection_id_seq OWNED BY public.collection.collection_id;


--
-- Name: country; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.country (
    country_id integer NOT NULL,
    name character varying(100) NOT NULL
);


ALTER TABLE public.country OWNER TO postgres;

--
-- Name: country_country_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.country_country_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.country_country_id_seq OWNER TO postgres;

--
-- Name: country_country_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.country_country_id_seq OWNED BY public.country.country_id;


--
-- Name: eav_table; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.eav_table (
    value_id integer NOT NULL,
    model_kit_id integer NOT NULL,
    attribute_id integer NOT NULL,
    value character varying(100)
);


ALTER TABLE public.eav_table OWNER TO postgres;

--
-- Name: eav_table_value_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.eav_table_value_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.eav_table_value_id_seq OWNER TO postgres;

--
-- Name: eav_table_value_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.eav_table_value_id_seq OWNED BY public.eav_table.value_id;


--
-- Name: follower_list; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.follower_list (
    follower_id integer NOT NULL,
    followed_id integer NOT NULL
);


ALTER TABLE public.follower_list OWNER TO postgres;

--
-- Name: likes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.likes (
    user_id integer NOT NULL,
    review_id integer NOT NULL,
    is_like boolean NOT NULL
);


ALTER TABLE public.likes OWNER TO postgres;

--
-- Name: manufacturer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.manufacturer (
    manufacturer_id integer NOT NULL,
    name character varying(100)
);


ALTER TABLE public.manufacturer OWNER TO postgres;

--
-- Name: manufacturer_manufacturer_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.manufacturer_manufacturer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.manufacturer_manufacturer_id_seq OWNER TO postgres;

--
-- Name: manufacturer_manufacturer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.manufacturer_manufacturer_id_seq OWNED BY public.manufacturer.manufacturer_id;


--
-- Name: model_kit; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.model_kit (
    model_kit_id integer NOT NULL,
    variant_id integer NOT NULL,
    manufacturer_id integer NOT NULL,
    use_country_id integer NOT NULL,
    name character varying(100) NOT NULL,
    manufacturer_code character varying(100) NOT NULL
);


ALTER TABLE public.model_kit OWNER TO postgres;

--
-- Name: model_kit_model_kit_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.model_kit_model_kit_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.model_kit_model_kit_id_seq OWNER TO postgres;

--
-- Name: model_kit_model_kit_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.model_kit_model_kit_id_seq OWNED BY public.model_kit.model_kit_id;


--
-- Name: photo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.photo (
    photo_id integer NOT NULL,
    model_kit_id integer NOT NULL,
    image character varying(200) NOT NULL,
    is_main boolean DEFAULT false NOT NULL
);


ALTER TABLE public.photo OWNER TO postgres;

--
-- Name: photo_photo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.photo_photo_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.photo_photo_id_seq OWNER TO postgres;

--
-- Name: photo_photo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.photo_photo_id_seq OWNED BY public.photo.photo_id;


--
-- Name: progress; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.progress (
    progress_id integer NOT NULL,
    name character varying(100) NOT NULL
);


ALTER TABLE public.progress OWNER TO postgres;

--
-- Name: progress_progress_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.progress_progress_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.progress_progress_id_seq OWNER TO postgres;

--
-- Name: progress_progress_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.progress_progress_id_seq OWNED BY public.progress.progress_id;


--
-- Name: reason; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reason (
    reason_id integer NOT NULL,
    reason_name character varying NOT NULL
);


ALTER TABLE public.reason OWNER TO postgres;

--
-- Name: reason_reason_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.reason_reason_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.reason_reason_id_seq OWNER TO postgres;

--
-- Name: reason_reason_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.reason_reason_id_seq OWNED BY public.reason.reason_id;


--
-- Name: report; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.report (
    report_id integer NOT NULL,
    review_id integer NOT NULL,
    user_id integer NOT NULL,
    reason_id integer NOT NULL,
    report_status_id integer NOT NULL
);


ALTER TABLE public.report OWNER TO postgres;

--
-- Name: report_report_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.report_report_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.report_report_id_seq OWNER TO postgres;

--
-- Name: report_report_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.report_report_id_seq OWNED BY public.report.report_id;


--
-- Name: report_status; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.report_status (
    report_status_id integer NOT NULL,
    status_name character varying(100) NOT NULL
);


ALTER TABLE public.report_status OWNER TO postgres;

--
-- Name: report_status_report_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.report_status_report_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.report_status_report_id_seq OWNER TO postgres;

--
-- Name: report_status_report_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.report_status_report_id_seq OWNED BY public.report_status.report_status_id;


--
-- Name: review; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.review (
    review_id integer NOT NULL,
    user_id integer NOT NULL,
    model_kit_id integer NOT NULL,
    rating integer NOT NULL,
    title character varying(40) NOT NULL,
    content character varying(256) NOT NULL,
    write_date date NOT NULL,
    review_status_id integer NOT NULL
);


ALTER TABLE public.review OWNER TO postgres;

--
-- Name: review_review_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.review_review_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.review_review_id_seq OWNER TO postgres;

--
-- Name: review_review_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.review_review_id_seq OWNED BY public.review.review_id;


--
-- Name: review_status; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.review_status (
    review_status_id integer NOT NULL,
    name character varying NOT NULL
);


ALTER TABLE public.review_status OWNER TO postgres;

--
-- Name: review_status_id_review_status_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.review_status_id_review_status_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.review_status_id_review_status_id_seq OWNER TO postgres;

--
-- Name: review_status_id_review_status_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.review_status_id_review_status_id_seq OWNED BY public.review_status.review_status_id;


--
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."user" (
    user_id integer NOT NULL,
    account_type_id integer NOT NULL,
    email character varying(100) NOT NULL,
    username character varying(40) NOT NULL,
    password character varying(256) NOT NULL,
    description character varying(256),
    created date NOT NULL,
    avatar character varying(256)
);


ALTER TABLE public."user" OWNER TO postgres;

--
-- Name: user_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.user_user_id_seq OWNER TO postgres;

--
-- Name: user_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_user_id_seq OWNED BY public."user".user_id;


--
-- Name: variant; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.variant (
    variant_id integer NOT NULL,
    vehicle_id integer NOT NULL,
    name character varying(100) NOT NULL
);


ALTER TABLE public.variant OWNER TO postgres;

--
-- Name: variant_variant_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.variant_variant_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.variant_variant_id_seq OWNER TO postgres;

--
-- Name: variant_variant_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.variant_variant_id_seq OWNED BY public.variant.variant_id;


--
-- Name: vehicle; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.vehicle (
    vehicle_id integer NOT NULL,
    country_id integer NOT NULL,
    name character varying(100) NOT NULL,
    vehicle_type_id integer NOT NULL,
    vehicle_photo character varying(200),
    generation character varying(10)
);


ALTER TABLE public.vehicle OWNER TO postgres;

--
-- Name: vehicle_type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.vehicle_type (
    vehicle_type_id integer NOT NULL,
    name character varying(100)
);


ALTER TABLE public.vehicle_type OWNER TO postgres;

--
-- Name: vehicle_type_vehicle_type_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.vehicle_type_vehicle_type_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.vehicle_type_vehicle_type_id_seq OWNER TO postgres;

--
-- Name: vehicle_type_vehicle_type_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.vehicle_type_vehicle_type_id_seq OWNED BY public.vehicle_type.vehicle_type_id;


--
-- Name: vehicle_vehicle_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.vehicle_vehicle_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.vehicle_vehicle_id_seq OWNER TO postgres;

--
-- Name: vehicle_vehicle_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.vehicle_vehicle_id_seq OWNED BY public.vehicle.vehicle_id;


--
-- Name: wishlist; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.wishlist (
    model_kit_id integer NOT NULL,
    user_id integer NOT NULL
);


ALTER TABLE public.wishlist OWNER TO postgres;

--
-- Name: account_type account_type_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account_type ALTER COLUMN account_type_id SET DEFAULT nextval('public.account_type_account_type_id_seq'::regclass);


--
-- Name: attribute attribute_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attribute ALTER COLUMN attribute_id SET DEFAULT nextval('public.attribute_attribute_id_seq'::regclass);


--
-- Name: collectible collectible_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.collectible ALTER COLUMN collectible_id SET DEFAULT nextval('public.collectible_collectible_id_seq'::regclass);


--
-- Name: collection collection_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.collection ALTER COLUMN collection_id SET DEFAULT nextval('public.collection_collection_id_seq'::regclass);


--
-- Name: country country_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.country ALTER COLUMN country_id SET DEFAULT nextval('public.country_country_id_seq'::regclass);


--
-- Name: eav_table value_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.eav_table ALTER COLUMN value_id SET DEFAULT nextval('public.eav_table_value_id_seq'::regclass);


--
-- Name: manufacturer manufacturer_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.manufacturer ALTER COLUMN manufacturer_id SET DEFAULT nextval('public.manufacturer_manufacturer_id_seq'::regclass);


--
-- Name: model_kit model_kit_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.model_kit ALTER COLUMN model_kit_id SET DEFAULT nextval('public.model_kit_model_kit_id_seq'::regclass);


--
-- Name: photo photo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.photo ALTER COLUMN photo_id SET DEFAULT nextval('public.photo_photo_id_seq'::regclass);


--
-- Name: progress progress_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.progress ALTER COLUMN progress_id SET DEFAULT nextval('public.progress_progress_id_seq'::regclass);


--
-- Name: reason reason_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reason ALTER COLUMN reason_id SET DEFAULT nextval('public.reason_reason_id_seq'::regclass);


--
-- Name: report report_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.report ALTER COLUMN report_id SET DEFAULT nextval('public.report_report_id_seq'::regclass);


--
-- Name: report_status report_status_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.report_status ALTER COLUMN report_status_id SET DEFAULT nextval('public.report_status_report_id_seq'::regclass);


--
-- Name: review review_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review ALTER COLUMN review_id SET DEFAULT nextval('public.review_review_id_seq'::regclass);


--
-- Name: review_status review_status_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review_status ALTER COLUMN review_status_id SET DEFAULT nextval('public.review_status_id_review_status_id_seq'::regclass);


--
-- Name: user user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user" ALTER COLUMN user_id SET DEFAULT nextval('public.user_user_id_seq'::regclass);


--
-- Name: variant variant_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.variant ALTER COLUMN variant_id SET DEFAULT nextval('public.variant_variant_id_seq'::regclass);


--
-- Name: vehicle vehicle_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vehicle ALTER COLUMN vehicle_id SET DEFAULT nextval('public.vehicle_vehicle_id_seq'::regclass);


--
-- Name: vehicle_type vehicle_type_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vehicle_type ALTER COLUMN vehicle_type_id SET DEFAULT nextval('public.vehicle_type_vehicle_type_id_seq'::regclass);


--
-- Name: account_type account_type_name_name1_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account_type
    ADD CONSTRAINT account_type_name_name1_key UNIQUE (name) INCLUDE (name);


--
-- Name: account_type account_type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account_type
    ADD CONSTRAINT account_type_pkey PRIMARY KEY (account_type_id);


--
-- Name: attribute attribute_name_attribute_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attribute
    ADD CONSTRAINT attribute_name_attribute_id_key UNIQUE (name) INCLUDE (attribute_id);


--
-- Name: attribute attribute_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attribute
    ADD CONSTRAINT attribute_pkey PRIMARY KEY (attribute_id);


--
-- Name: collectible collectible_collection_id_model_kit_id_model_kit_id1_collec_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.collectible
    ADD CONSTRAINT collectible_collection_id_model_kit_id_model_kit_id1_collec_key UNIQUE (collection_id, model_kit_id) INCLUDE (model_kit_id, collection_id);


--
-- Name: collectible collectible_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.collectible
    ADD CONSTRAINT collectible_pkey PRIMARY KEY (collectible_id);


--
-- Name: collection collection_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.collection
    ADD CONSTRAINT collection_pkey PRIMARY KEY (collection_id);


--
-- Name: country country_name_name1_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.country
    ADD CONSTRAINT country_name_name1_key UNIQUE (name) INCLUDE (name);


--
-- Name: country country_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.country
    ADD CONSTRAINT country_pkey PRIMARY KEY (country_id);


--
-- Name: eav_table eav_table_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.eav_table
    ADD CONSTRAINT eav_table_pkey PRIMARY KEY (value_id);


--
-- Name: follower_list follower_list_followerId_followedId_followerId1_followedId1_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.follower_list
    ADD CONSTRAINT "follower_list_followerId_followedId_followerId1_followedId1_key" UNIQUE (follower_id, followed_id) INCLUDE (follower_id, followed_id);


--
-- Name: follower_list follower_list_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.follower_list
    ADD CONSTRAINT follower_list_pkey PRIMARY KEY (followed_id, follower_id);


--
-- Name: likes likes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.likes
    ADD CONSTRAINT likes_pkey PRIMARY KEY (user_id, review_id);


--
-- Name: manufacturer manufacturer_name_name1_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.manufacturer
    ADD CONSTRAINT manufacturer_name_name1_key UNIQUE (name) INCLUDE (name);


--
-- Name: manufacturer manufacturer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.manufacturer
    ADD CONSTRAINT manufacturer_pkey PRIMARY KEY (manufacturer_id);


--
-- Name: model_kit model_kit_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.model_kit
    ADD CONSTRAINT model_kit_pkey PRIMARY KEY (model_kit_id);


--
-- Name: photo photo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.photo
    ADD CONSTRAINT photo_pkey PRIMARY KEY (photo_id);


--
-- Name: progress progress_name_name1_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.progress
    ADD CONSTRAINT progress_name_name1_key UNIQUE (name) INCLUDE (name);


--
-- Name: progress progress_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.progress
    ADD CONSTRAINT progress_pkey PRIMARY KEY (progress_id);


--
-- Name: reason reason_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reason
    ADD CONSTRAINT reason_pkey PRIMARY KEY (reason_id);


--
-- Name: reason reason_reason_name_reason_name1_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reason
    ADD CONSTRAINT reason_reason_name_reason_name1_key UNIQUE (reason_name) INCLUDE (reason_name);


--
-- Name: report report_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.report
    ADD CONSTRAINT report_pkey PRIMARY KEY (report_id);


--
-- Name: report_status report_status_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.report_status
    ADD CONSTRAINT report_status_pkey PRIMARY KEY (report_status_id);


--
-- Name: report_status report_status_status_name_status_name1_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.report_status
    ADD CONSTRAINT report_status_status_name_status_name1_key UNIQUE (status_name) INCLUDE (status_name);


--
-- Name: review review_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review
    ADD CONSTRAINT review_pkey PRIMARY KEY (review_id);


--
-- Name: review_status review_status_id_name_name1_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review_status
    ADD CONSTRAINT review_status_id_name_name1_key UNIQUE (name) INCLUDE (name);


--
-- Name: review_status review_status_id_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review_status
    ADD CONSTRAINT review_status_id_pkey PRIMARY KEY (review_status_id);


--
-- Name: review review_user_id_model_kit_id_user_id1_model_kit_id1_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review
    ADD CONSTRAINT review_user_id_model_kit_id_user_id1_model_kit_id1_key UNIQUE (user_id, model_kit_id) INCLUDE (user_id, model_kit_id);


--
-- Name: user user_email_email1_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_email_email1_key UNIQUE (email) INCLUDE (email);


--
-- Name: user user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);


--
-- Name: user user_username_username1_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_username_username1_key UNIQUE (username) INCLUDE (username);


--
-- Name: variant variant_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.variant
    ADD CONSTRAINT variant_pkey PRIMARY KEY (variant_id);


--
-- Name: vehicle vehicle_name_name1_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vehicle
    ADD CONSTRAINT vehicle_name_name1_key UNIQUE (name) INCLUDE (name);


--
-- Name: vehicle vehicle_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vehicle
    ADD CONSTRAINT vehicle_pkey PRIMARY KEY (vehicle_id);


--
-- Name: vehicle_type vehicle_type_name_name1_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vehicle_type
    ADD CONSTRAINT vehicle_type_name_name1_key UNIQUE (name) INCLUDE (name);


--
-- Name: vehicle_type vehicle_type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vehicle_type
    ADD CONSTRAINT vehicle_type_pkey PRIMARY KEY (vehicle_type_id);


--
-- Name: wishlist wishlist_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wishlist
    ADD CONSTRAINT wishlist_pkey PRIMARY KEY (model_kit_id, user_id);


--
-- Name: collectible collectible_collection_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.collectible
    ADD CONSTRAINT collectible_collection_id_fkey FOREIGN KEY (collection_id) REFERENCES public.collection(collection_id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- Name: collectible collectible_model_kit_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.collectible
    ADD CONSTRAINT collectible_model_kit_id_fkey FOREIGN KEY (model_kit_id) REFERENCES public.model_kit(model_kit_id);


--
-- Name: collectible collectible_progress_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.collectible
    ADD CONSTRAINT collectible_progress_id_fkey FOREIGN KEY (progress_id) REFERENCES public.progress(progress_id) NOT VALID;


--
-- Name: collection collection_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.collection
    ADD CONSTRAINT collection_user_id_fkey FOREIGN KEY (user_id) REFERENCES public."user"(user_id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- Name: eav_table eav_table_attribute_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.eav_table
    ADD CONSTRAINT eav_table_attribute_id_fkey FOREIGN KEY (attribute_id) REFERENCES public.attribute(attribute_id);


--
-- Name: eav_table eav_table_model_kit_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.eav_table
    ADD CONSTRAINT eav_table_model_kit_id_fkey FOREIGN KEY (model_kit_id) REFERENCES public.model_kit(model_kit_id);


--
-- Name: follower_list follower_list_followed_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.follower_list
    ADD CONSTRAINT follower_list_followed_id_fkey FOREIGN KEY (followed_id) REFERENCES public."user"(user_id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- Name: follower_list follower_list_follower_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.follower_list
    ADD CONSTRAINT follower_list_follower_id_fkey FOREIGN KEY (follower_id) REFERENCES public."user"(user_id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- Name: likes likes_review_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.likes
    ADD CONSTRAINT likes_review_id_fkey FOREIGN KEY (review_id) REFERENCES public.review(review_id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- Name: likes likes_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.likes
    ADD CONSTRAINT likes_user_id_fkey FOREIGN KEY (user_id) REFERENCES public."user"(user_id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- Name: model_kit model_kit_manufacturer_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.model_kit
    ADD CONSTRAINT model_kit_manufacturer_id_fkey FOREIGN KEY (manufacturer_id) REFERENCES public.manufacturer(manufacturer_id) NOT VALID;


--
-- Name: model_kit model_kit_use_country_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.model_kit
    ADD CONSTRAINT model_kit_use_country_id_fkey FOREIGN KEY (use_country_id) REFERENCES public.country(country_id);


--
-- Name: model_kit model_kit_variant_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.model_kit
    ADD CONSTRAINT model_kit_variant_id_fkey FOREIGN KEY (variant_id) REFERENCES public.variant(variant_id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- Name: photo photo_model_kit_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.photo
    ADD CONSTRAINT photo_model_kit_id_fkey FOREIGN KEY (model_kit_id) REFERENCES public.model_kit(model_kit_id);


--
-- Name: report report_reason_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.report
    ADD CONSTRAINT report_reason_id_fkey FOREIGN KEY (reason_id) REFERENCES public.reason(reason_id) NOT VALID;


--
-- Name: report report_report_status_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.report
    ADD CONSTRAINT report_report_status_id_fkey FOREIGN KEY (report_status_id) REFERENCES public.report_status(report_status_id) NOT VALID;


--
-- Name: report report_review_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.report
    ADD CONSTRAINT report_review_id_fkey FOREIGN KEY (review_id) REFERENCES public.review(review_id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- Name: report report_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.report
    ADD CONSTRAINT report_user_id_fkey FOREIGN KEY (user_id) REFERENCES public."user"(user_id) NOT VALID;


--
-- Name: review review_model_kit_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review
    ADD CONSTRAINT review_model_kit_id_fkey FOREIGN KEY (model_kit_id) REFERENCES public.model_kit(model_kit_id);


--
-- Name: review review_review_status_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review
    ADD CONSTRAINT review_review_status_id_fkey FOREIGN KEY (review_status_id) REFERENCES public.review_status(review_status_id) NOT VALID;


--
-- Name: review review_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review
    ADD CONSTRAINT review_user_id_fkey FOREIGN KEY (user_id) REFERENCES public."user"(user_id);


--
-- Name: user user_account_type_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_account_type_id_fkey FOREIGN KEY (account_type_id) REFERENCES public.account_type(account_type_id) NOT VALID;


--
-- Name: variant variant_vehicle_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.variant
    ADD CONSTRAINT variant_vehicle_id_fkey FOREIGN KEY (vehicle_id) REFERENCES public.vehicle(vehicle_id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- Name: vehicle vehicle_country_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vehicle
    ADD CONSTRAINT vehicle_country_id_fkey FOREIGN KEY (country_id) REFERENCES public.country(country_id) NOT VALID;


--
-- Name: vehicle vehicle_vehicle_type_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vehicle
    ADD CONSTRAINT vehicle_vehicle_type_id_fkey FOREIGN KEY (vehicle_type_id) REFERENCES public.vehicle_type(vehicle_type_id) NOT VALID;


--
-- Name: wishlist wishlist_model_kit_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wishlist
    ADD CONSTRAINT wishlist_model_kit_id_fkey FOREIGN KEY (model_kit_id) REFERENCES public.model_kit(model_kit_id);


--
-- Name: wishlist wishlist_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wishlist
    ADD CONSTRAINT wishlist_user_id_fkey FOREIGN KEY (user_id) REFERENCES public."user"(user_id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- PostgreSQL database dump complete
--

