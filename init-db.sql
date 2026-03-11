-- This script runs automatically when the PostgreSQL container starts for the first time.
-- It creates one database per service (each service owns its data independently).

CREATE DATABASE userdb;
CREATE DATABASE mediadb;
CREATE DATABASE subscriptiondb;
