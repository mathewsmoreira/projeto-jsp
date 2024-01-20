-- Database: projeto_jsp

-- DROP DATABASE IF EXISTS cprojeto_jsp;

-- Database: projeto_jsp

-- DROP DATABASE IF EXISTS projeto_jsp;
CREATE DATABASE projeto_jsp
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = false;

COMMENT ON DATABASE projeto_jsp
    IS 'banco criado para servir como exemplo nas aulas do curso jdev treinamentos modulo de JSP.';