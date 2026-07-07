--
-- PostgreSQL database dump
--

\restrict VpQy2zXh6pnFOhWAbvNZH8mhHlHLdegiFfJIa4qvje3oaRpgC1haY91kiAdDpOr

-- Dumped from database version 18.4
-- Dumped by pg_dump version 18.4

-- Started on 2026-07-08 02:25:47

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
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
-- TOC entry 222 (class 1259 OID 16413)
-- Name: product; Type: TABLE; Schema: public; Owner: robsibe
--

CREATE TABLE public.product (
    id integer NOT NULL,
    name character(20) NOT NULL,
    price integer,
    CONSTRAINT product_price_check CHECK ((price > '-1'::integer))
);


ALTER TABLE public.product OWNER TO robsibe;

--
-- TOC entry 221 (class 1259 OID 16412)
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: robsibe
--

CREATE SEQUENCE public.product_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.product_id_seq OWNER TO robsibe;

--
-- TOC entry 4985 (class 0 OID 0)
-- Dependencies: 221
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: robsibe
--

ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;


--
-- TOC entry 4829 (class 2604 OID 16416)
-- Name: product id; Type: DEFAULT; Schema: public; Owner: robsibe
--

ALTER TABLE ONLY public.product ALTER COLUMN id SET DEFAULT nextval('public.product_id_seq'::regclass);


--
-- TOC entry 4979 (class 0 OID 16413)
-- Dependencies: 222
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: robsibe
--

COPY public.product (id, name, price) FROM stdin;
1	tomato              	20
2	cucumber            	15
3	tea                 	25
4	coffee              	30
\.


--
-- TOC entry 4986 (class 0 OID 0)
-- Dependencies: 221
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: robsibe
--

SELECT pg_catalog.setval('public.product_id_seq', 4, true);


-- Completed on 2026-07-08 02:25:48

--
-- PostgreSQL database dump complete
--

\unrestrict VpQy2zXh6pnFOhWAbvNZH8mhHlHLdegiFfJIa4qvje3oaRpgC1haY91kiAdDpOr

