2CREATE DATABASE  IF NOT EXISTS `zoologico` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `zoologico`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: zoologico
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.28-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `alimentacao`
--

DROP TABLE IF EXISTS `alimentacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alimentacao` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOME_DIETA` varchar(45) DEFAULT NULL,
  `DESCRICAO` varchar(250) DEFAULT NULL,
  `NUTRIENTES_ESSENCIAIS` varchar(250) DEFAULT NULL,
  `QUANTIDADE_RECOMENDADA` varchar(45) DEFAULT NULL,
  `FREQUENCIA` varchar(45) DEFAULT NULL,
  `ESPECIE_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ESPECIE_ID` (`ESPECIE_ID`),
  CONSTRAINT `alimentacao_ibfk_1` FOREIGN KEY (`ESPECIE_ID`) REFERENCES `especie` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alimentacao`
--

LOCK TABLES `alimentacao` WRITE;
/*!40000 ALTER TABLE `alimentacao` DISABLE KEYS */;
/*!40000 ALTER TABLE `alimentacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `animal`
--

DROP TABLE IF EXISTS `animal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `animal` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOME` varchar(45) DEFAULT NULL,
  `DATA_NASCIMENTO` date DEFAULT NULL,
  `COR` varchar(45) DEFAULT NULL,
  `TAMANHO` varchar(45) DEFAULT NULL,
  `DESCRICAO` varchar(250) DEFAULT NULL,
  `ESPECIE_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ESPECIE_ID` (`ESPECIE_ID`),
  CONSTRAINT `animal_ibfk_1` FOREIGN KEY (`ESPECIE_ID`) REFERENCES `especie` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animal`
--

LOCK TABLES `animal` WRITE;
/*!40000 ALTER TABLE `animal` DISABLE KEYS */;
/*!40000 ALTER TABLE `animal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `atividade`
--

DROP TABLE IF EXISTS `atividade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `atividade` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRICAO` varchar(100) DEFAULT NULL,
  `DURACAO` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `atividade`
--

LOCK TABLES `atividade` WRITE;
/*!40000 ALTER TABLE `atividade` DISABLE KEYS */;
INSERT INTO `atividade` VALUES (1,'Alimentação dos animais',25),(2,'Apresentação de animais',20),(3,'Passeio de trenzinho pelo zoológico',30),(4,'Observação dos animais em seus recintos',NULL),(5,'Palestras educacionais',35),(6,'Áreas de recreação infantil',NULL),(7,'Exposições interativas',60),(8,'Trilhas ecológicas',NULL),(9,'Encontros com cuidadores ou treinadores',30),(10,'Experiências de alimentação de animais sob supervisão',30),(11,'Alimentação dos animais',25),(12,'Apresentação de animais',20),(13,'Passeio de trenzinho pelo zoológico',30),(14,'Observação dos animais em seus recintos',NULL),(15,'Palestras educacionais',35),(16,'Áreas de recreação infantil',NULL),(17,'Exposições interativas',60),(18,'Trilhas ecológicas',NULL),(19,'Encontros com cuidadores ou treinadores',30),(20,'Experiências de alimentação de animais sob supervisão',30);
/*!40000 ALTER TABLE `atividade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cargo`
--

DROP TABLE IF EXISTS `cargo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cargo` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITULO` varchar(100) DEFAULT NULL,
  `DESCRICAO` varchar(250) DEFAULT NULL,
  `SALARIO_BASE` decimal(7,2) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cargo`
--

LOCK TABLES `cargo` WRITE;
/*!40000 ALTER TABLE `cargo` DISABLE KEYS */;
INSERT INTO `cargo` VALUES (1,'Gerente','Responsável pela gestão geral do zoológico. Suas responsabilidades incluem a supervisão de todos os departamentos, o planejamento estratégico, a administração de recursos financeiros e humanos, a garantia da segurança dos visitantes e animais.',60000.00),(2,'Pesquisador','Se dedica a estudos científicos relacionados à fauna do zoológico. Isso pode incluir pesquisa comportamental, estudos de reprodução, pesquisas de campo e projetos de conservação de espécies. Ele também pode colaborar com universidades e instituições ',40000.00),(3,'Treinador','Treinadores são especializados em cuidar e treinar os animais. Eles garantem que os animais estejam saudáveis, treinados para interagir com os visitantes e participar em apresentações. O treinamento é baseado em métodos positivos de reforço.',50000.00),(4,'Cuidador','Cuidadores são responsáveis pelos cuidados diários dos animais. Isso inclui alimentação, limpeza de recintos, monitoramento da saúde dos animais e garantia de que todas as suas necessidades sejam atendidas.',40000.00),(5,'Veterinário','Veterinários especializados em animais selvagens são responsáveis pelo cuidado médico dos animais do zoológico. Isso inclui diagnóstico, tratamento de doenças, cirurgias quando necessário, e garantir a saúde dos animais',70000.00),(6,'Atendente de bilheteria','Atendente é responsável pelo atendimento aos visitantes, realizando a venda de ingressos e fornecendo informações importantes. Também fica responsável por sanar dúvidas dos visitantes.',NULL),(7,'Guia turístico','Tem a responsabilidade de auxiliar os visitantes durante as visitas ao zoológico, informar sobre o funcionamento. É responsável também por guiar os visitantes onde for necessário, fornecendo informações e curiosidades.',NULL),(8,'Segurança','O segurança tem por responsabilidade zelar pelo bem de todos dentro do zoológico, podendo agir conforme o treinamento recebido.',NULL),(9,'Zelador','O zelador é responsável pelo bom funcionamento do zoológico, bem como a limpeza e higiene do ambiente dos visitantes e dos animais',NULL);
/*!40000 ALTER TABLE `cargo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contato`
--

DROP TABLE IF EXISTS `contato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contato` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TIPO_CONTATO` varchar(1) DEFAULT NULL,
  `CONTATO` varchar(200) DEFAULT NULL,
  `FUNCIONARIO_ID` int(11) DEFAULT NULL,
  `VISITANTE_ID` int(11) DEFAULT NULL,
  `FORNECEDOR_ID` int(11) DEFAULT NULL,
  `ZOOLOGICO_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FUNCIONARIO_ID` (`FUNCIONARIO_ID`),
  KEY `VISITANTE_ID` (`VISITANTE_ID`),
  KEY `FORNECEDOR_ID` (`FORNECEDOR_ID`),
  KEY `ZOOLOGICO_ID` (`ZOOLOGICO_ID`),
  CONSTRAINT `contato_ibfk_1` FOREIGN KEY (`FUNCIONARIO_ID`) REFERENCES `funcionario` (`ID`),
  CONSTRAINT `contato_ibfk_2` FOREIGN KEY (`VISITANTE_ID`) REFERENCES `visitante` (`ID`),
  CONSTRAINT `contato_ibfk_3` FOREIGN KEY (`FORNECEDOR_ID`) REFERENCES `fornecedor` (`ID`),
  CONSTRAINT `contato_ibfk_4` FOREIGN KEY (`ZOOLOGICO_ID`) REFERENCES `zoologico` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contato`
--

LOCK TABLES `contato` WRITE;
/*!40000 ALTER TABLE `contato` DISABLE KEYS */;
/*!40000 ALTER TABLE `contato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuidador`
--

DROP TABLE IF EXISTS `cuidador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuidador` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ESPECIALIZACAO` varchar(100) DEFAULT NULL,
  `RESPONSABILIDADE` varchar(100) NOT NULL,
  `FUNCIONARIO_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FUNCIONARIO_ID` (`FUNCIONARIO_ID`),
  CONSTRAINT `cuidador_ibfk_1` FOREIGN KEY (`FUNCIONARIO_ID`) REFERENCES `funcionario` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuidador`
--

LOCK TABLES `cuidador` WRITE;
/*!40000 ALTER TABLE `cuidador` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuidador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuidados`
--

DROP TABLE IF EXISTS `cuidados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuidados` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TIPO` varchar(45) DEFAULT NULL,
  `DESCRICAO` varchar(250) DEFAULT NULL,
  `ESPECIE_ID` int(11) NOT NULL,
  `VETERINARIO_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ESPECIE_ID` (`ESPECIE_ID`),
  KEY `VETERINARIO_ID` (`VETERINARIO_ID`),
  CONSTRAINT `cuidados_ibfk_1` FOREIGN KEY (`ESPECIE_ID`) REFERENCES `especie` (`ID`),
  CONSTRAINT `cuidados_ibfk_2` FOREIGN KEY (`VETERINARIO_ID`) REFERENCES `veterinario` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuidados`
--

LOCK TABLES `cuidados` WRITE;
/*!40000 ALTER TABLE `cuidados` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuidados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departamento`
--

DROP TABLE IF EXISTS `departamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `departamento` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOME` varchar(100) DEFAULT NULL,
  `DESCRICAO` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departamento`
--

LOCK TABLES `departamento` WRITE;
/*!40000 ALTER TABLE `departamento` DISABLE KEYS */;
INSERT INTO `departamento` VALUES (1,'Departamento de Zoologia','Responsável pelo cuidado e estudo das espécies, garantindo o bem-estar dos animais.'),(2,'Departamento de Educação Ambiental','Promove a conscientização ambiental por meio de programas educacionais e interação com visitantes.'),(3,'Departamento de Alimentação dos Animais','Fornecem dietas balanceadas e cuidados nutricionais para os animais.'),(4,'Departamento de Veterinária','Cuida da saúde e bem-estar dos animais, realizando tratamentos e exames médicos.'),(5,'Departamento de Marketing','Promove o zoológico, atrai visitantes e divulga programas especiais.'),(6,'Departamento de Recursos Humanos','Gerencia questões de pessoal, contratações, treinamentos e desenvolvimento de funcionários.'),(7,'Departamento de Financeiro','Responsável pelas finanças, orçamento e gerenciamento de recursos financeiros.'),(8,'Departamento de Segurança','Garante a segurança de visitantes, funcionários e animais, implementando medidas de proteção e emergência.'),(9,'Departamento de Manutenção','Mantém as instalações e infraestrutura do zoológico em condições ideais.');
/*!40000 ALTER TABLE `departamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endereco`
--

DROP TABLE IF EXISTS `endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `endereco` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PAIS` varchar(45) DEFAULT NULL,
  `ESTADO` varchar(45) DEFAULT NULL,
  `CIDADE` varchar(45) DEFAULT NULL,
  `LOGRADOURO` varchar(100) DEFAULT NULL,
  `COMPLEMENTO` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endereco`
--

LOCK TABLES `endereco` WRITE;
/*!40000 ALTER TABLE `endereco` DISABLE KEYS */;
/*!40000 ALTER TABLE `endereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `especie`
--

DROP TABLE IF EXISTS `especie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `especie` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOME` varchar(100) DEFAULT NULL,
  `DESCRICAO` varchar(250) DEFAULT NULL,
  `CLASSIFICACAO` varchar(100) DEFAULT NULL,
  `ORIGEM` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `especie`
--

LOCK TABLES `especie` WRITE;
/*!40000 ALTER TABLE `especie` DISABLE KEYS */;
INSERT INTO `especie` VALUES (1,'Leão','Felino majestoso com juba, o rei da savana africana.','Panthera leo.','África'),(2,'Tigre','Maior felino do mundo, com listras impressionantes.','Panthera tigris.','Ásia'),(3,'Girafa','Mamífero de pescoço longo, herbívoro e de aparência única.','Giraffa camelopardalis.','África'),(4,'Elefante',' Gigante terrestre com tromba e presas enormes.',' Elephas maximus (elefante asiático) e Loxodonta africana (elefante africano).','África e Ásia.'),(5,'Macaco-prego','Macaco de médio porte com pelagem escura e cauda preênsil.',' Sapajus libidinosus.','América do Sul.'),(6,'Pinguim-de-magalhães','Pinguim de tamanho médio, com plumagem preta e branca.','Spheniscus magellanicus.','América do Sul.'),(7,'Zebra','Mamífero listrado preto e branco com crina ereta.','Equus zebra.','África'),(8,'Serpente-píton','Cobra constritora de grande porte com escamas.','Pythonidae','África, Ásia e Oceania.'),(9,'Tartaruga-marinha','Réptil marinho com casco, conhecido por migrações.','Diversas espécies, como a tartaruga-verde (Chelonia mydas).','Oceanos em todo o mundo.'),(10,'Lobo-guará','Canídeo de pelagem avermelhada, solitário e noturno.','Chrysocyon brachyurus.','América do Sul.'),(11,'Panda-vermelho','Pequeno mamífero com pelagem vermelha e cauda longa.','Ailurus fulgens.','Regiões montanhosas da Ásia.'),(12,'Golfinho-comum','Cetáceo marinho de tamanho médio com nadadeira dorsal.','Delphinus delphis','Oceanos em todo o mundo.'),(13,'Urso-pardo','Grande urso de pelagem marrom escuro.','Ursus arctos.','América do Norte, Europa e Ásia.'),(14,'Flamingo-rosa','Ave com longas pernas e plumagem rosa intensa.','Phoenicopterus roseus.','África, Ásia e Europa.'),(15,'Jacaré-do-pantanal','Réptil semi-aquático com focinho largo.','Caiman yacare.','América do Sul, especialmente no Pantanal.'),(16,'Rinoceronte-negro','Grande herbívoro com pele cinza e chifres.',' Diceros bicornis.','África'),(17,'Canguru-vermelho','Marsupial saltador com pelagem vermelha.','Macropus rufus.','Austrália'),(18,'Gorila-das-montanhas',' Maior primata do mundo, com pelagem escura.','Gorilla beringei beringei.','Montanhas da África Central.'),(19,'Tubarão-tigre','Tubarão de grande porte com padrões de listras.','Galeocerdo cuvier.','Oceanos tropicais e subtropicais.'),(20,'Arara-azul','Papagaio de plumagem azul intensa e bico preto.','Anodorhynchus hyacinthinus.','América do Sul.'),(21,'Hipopótamo-comum','Mamífero aquático de grande porte com pele grossa.','Hippopotamus amphibius.','África'),(22,'Leopardo-das-neves',' Felino de pelagem espessa adaptado às montanhas.','Panthera uncia.','Montanhas da Ásia Central.'),(23,'Orangotango-de-bornéu','Grande primata arbóreo com pelagem avermelhada.','Pongo pygmaeus.','Ilha de Bornéu (Sudeste Asiático).'),(24,'Chimpanzé-comum','Primata inteligente e sociável com parentesco humano.','Pan troglodytes.','África'),(25,'Peixe-palhaço','Peixe colorido com listras, famoso por viver em anêmonas.','Amphiprioninae','Oceanos do Indo-Pacífico.'),(26,'Peixe-boi','Mamífero marinho herbívoro com corpo robusto.','Trichechus spp. (diversas espécies).','Áreas costeiras tropicais.'),(27,'Lince-ibérico','Felino de pequeno porte com orelhas tufadas.','Lynx pardinus.','Península Ibérica.'),(28,'Suricata','Pequeno mamífero com comportamento social e vigilante.','Suricata suricatta.','África'),(29,'Tucano-de-bico-colorido','Ave com bico grande e colorido, famosa pela plumagem.','Ramphastos spp. (diversas espécies).','América Central e do Sul.'),(30,'Baleia-jubarte','Enorme cetáceo com padrões de coloração distintos.','Megaptera novaeangliae.','Oceanos do mundo todo.');
/*!40000 ALTER TABLE `especie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `especie_habitat`
--

DROP TABLE IF EXISTS `especie_habitat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `especie_habitat` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ESPECIE_ID` int(11) NOT NULL,
  `HABITAT_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ESPECIE_ID` (`ESPECIE_ID`),
  KEY `HABITAT_ID` (`HABITAT_ID`),
  CONSTRAINT `especie_habitat_ibfk_1` FOREIGN KEY (`ESPECIE_ID`) REFERENCES `especie` (`ID`),
  CONSTRAINT `especie_habitat_ibfk_2` FOREIGN KEY (`HABITAT_ID`) REFERENCES `habitat` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `especie_habitat`
--

LOCK TABLES `especie_habitat` WRITE;
/*!40000 ALTER TABLE `especie_habitat` DISABLE KEYS */;
/*!40000 ALTER TABLE `especie_habitat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fornecedor`
--

DROP TABLE IF EXISTS `fornecedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fornecedor` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NU_CNPJ` varchar(14) DEFAULT NULL,
  `RAZAO_SOCIAL` varchar(100) DEFAULT NULL,
  `ENDERECO_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ENDERECO_ID` (`ENDERECO_ID`),
  CONSTRAINT `fornecedor_ibfk_1` FOREIGN KEY (`ENDERECO_ID`) REFERENCES `endereco` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedor`
--

LOCK TABLES `fornecedor` WRITE;
/*!40000 ALTER TABLE `fornecedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `fornecedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `funcionario` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOME` varchar(45) NOT NULL,
  `SOBRENOME` varchar(45) NOT NULL,
  `ENDERECO_ID` int(11) NOT NULL,
  `ZOOLOGICO_ID` int(11) NOT NULL,
  `CARGO_ID` int(11) NOT NULL,
  `DEPARTAMENTO_ID` int(11) NOT NULL,
  `NU_CPF` varchar(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ENDERECO_ID` (`ENDERECO_ID`),
  KEY `ZOOLOGICO_ID` (`ZOOLOGICO_ID`),
  KEY `CARGO_ID` (`CARGO_ID`),
  KEY `DEPARTAMENTO_ID` (`DEPARTAMENTO_ID`),
  CONSTRAINT `funcionario_ibfk_1` FOREIGN KEY (`ENDERECO_ID`) REFERENCES `endereco` (`ID`),
  CONSTRAINT `funcionario_ibfk_2` FOREIGN KEY (`ZOOLOGICO_ID`) REFERENCES `zoologico` (`ID`),
  CONSTRAINT `funcionario_ibfk_3` FOREIGN KEY (`CARGO_ID`) REFERENCES `cargo` (`ID`),
  CONSTRAINT `funcionario_ibfk_4` FOREIGN KEY (`DEPARTAMENTO_ID`) REFERENCES `departamento` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gerente`
--

DROP TABLE IF EXISTS `gerente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gerente` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FUNCIONARIO_ID` int(11) NOT NULL,
  `DEPARTAMENTO_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FUNCIONARIO_ID` (`FUNCIONARIO_ID`),
  KEY `DEPARTAMENTO_ID` (`DEPARTAMENTO_ID`),
  CONSTRAINT `gerente_ibfk_1` FOREIGN KEY (`FUNCIONARIO_ID`) REFERENCES `funcionario` (`ID`),
  CONSTRAINT `gerente_ibfk_2` FOREIGN KEY (`DEPARTAMENTO_ID`) REFERENCES `departamento` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gerente`
--

LOCK TABLES `gerente` WRITE;
/*!40000 ALTER TABLE `gerente` DISABLE KEYS */;
/*!40000 ALTER TABLE `gerente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `habitat`
--

DROP TABLE IF EXISTS `habitat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `habitat` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOME` varchar(100) DEFAULT NULL,
  `DESCRICAO` varchar(250) DEFAULT NULL,
  `TAMANHO` varchar(45) DEFAULT NULL,
  `CLIMA` varchar(45) DEFAULT NULL,
  `VEGETACAO` varchar(100) DEFAULT NULL,
  `CUIDADOR_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `CUIDADOR_ID` (`CUIDADOR_ID`),
  CONSTRAINT `habitat_ibfk_1` FOREIGN KEY (`CUIDADOR_ID`) REFERENCES `cuidador` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `habitat`
--

LOCK TABLES `habitat` WRITE;
/*!40000 ALTER TABLE `habitat` DISABLE KEYS */;
/*!40000 ALTER TABLE `habitat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historico_saude`
--

DROP TABLE IF EXISTS `historico_saude`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `historico_saude` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DATA_REGISTRO` datetime DEFAULT NULL,
  `OBSERVACOES_MEDICAS` varchar(250) DEFAULT NULL,
  `PROCEDIMENTO_MEDICO` varchar(250) DEFAULT NULL,
  `RESULTADO_EXAME` varchar(250) DEFAULT NULL,
  `VETERINARIO_ID` int(11) NOT NULL,
  `ANIMAL_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `VETERINARIO_ID` (`VETERINARIO_ID`),
  KEY `ANIMAL_ID` (`ANIMAL_ID`),
  CONSTRAINT `historico_saude_ibfk_1` FOREIGN KEY (`VETERINARIO_ID`) REFERENCES `veterinario` (`ID`),
  CONSTRAINT `historico_saude_ibfk_2` FOREIGN KEY (`ANIMAL_ID`) REFERENCES `animal` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historico_saude`
--

LOCK TABLES `historico_saude` WRITE;
/*!40000 ALTER TABLE `historico_saude` DISABLE KEYS */;
/*!40000 ALTER TABLE `historico_saude` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingresso`
--

DROP TABLE IF EXISTS `ingresso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingresso` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DATA_COMPRPA` datetime DEFAULT NULL,
  `VALOR` decimal(5,2) DEFAULT NULL,
  `ZOOLOGICO_ID` int(11) NOT NULL,
  `VISITANTE_ID` int(11) DEFAULT NULL,
  `VISITA_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ZOOLOGICO_ID` (`ZOOLOGICO_ID`),
  KEY `VISITANTE_ID` (`VISITANTE_ID`),
  CONSTRAINT `ingresso_ibfk_1` FOREIGN KEY (`ZOOLOGICO_ID`) REFERENCES `zoologico` (`ID`),
  CONSTRAINT `ingresso_ibfk_2` FOREIGN KEY (`VISITANTE_ID`) REFERENCES `visitante` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingresso`
--

LOCK TABLES `ingresso` WRITE;
/*!40000 ALTER TABLE `ingresso` DISABLE KEYS */;
/*!40000 ALTER TABLE `ingresso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pesquisador`
--

DROP TABLE IF EXISTS `pesquisador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pesquisador` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `AREA_PESQUISA` varchar(100) NOT NULL,
  `FUNCIONARIO_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FUNCIONARIO_ID` (`FUNCIONARIO_ID`),
  CONSTRAINT `pesquisador_ibfk_1` FOREIGN KEY (`FUNCIONARIO_ID`) REFERENCES `funcionario` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pesquisador`
--

LOCK TABLES `pesquisador` WRITE;
/*!40000 ALTER TABLE `pesquisador` DISABLE KEYS */;
/*!40000 ALTER TABLE `pesquisador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `treinamento`
--

DROP TABLE IF EXISTS `treinamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `treinamento` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOME` varchar(100) DEFAULT NULL,
  `DESCRICAO` varchar(250) DEFAULT NULL,
  `TECNICA` varchar(250) DEFAULT NULL,
  `DURACAO` varchar(45) DEFAULT NULL,
  `FREQUENCIA` varchar(45) DEFAULT NULL,
  `ESPECIE_ID` int(11) NOT NULL,
  `CUIDADOR_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ESPECIE_ID` (`ESPECIE_ID`),
  KEY `CUIDADOR_ID` (`CUIDADOR_ID`),
  CONSTRAINT `treinamento_ibfk_1` FOREIGN KEY (`ESPECIE_ID`) REFERENCES `especie` (`ID`),
  CONSTRAINT `treinamento_ibfk_2` FOREIGN KEY (`CUIDADOR_ID`) REFERENCES `cuidador` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `treinamento`
--

LOCK TABLES `treinamento` WRITE;
/*!40000 ALTER TABLE `treinamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `treinamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `veterinario`
--

DROP TABLE IF EXISTS `veterinario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `veterinario` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ESPECIALIDADE_VETERINARIA` varchar(100) NOT NULL,
  `FUNCIONARIO_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FUNCIONARIO_ID` (`FUNCIONARIO_ID`),
  CONSTRAINT `veterinario_ibfk_1` FOREIGN KEY (`FUNCIONARIO_ID`) REFERENCES `funcionario` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `veterinario`
--

LOCK TABLES `veterinario` WRITE;
/*!40000 ALTER TABLE `veterinario` DISABLE KEYS */;
/*!40000 ALTER TABLE `veterinario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visita`
--

DROP TABLE IF EXISTS `visita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `visita` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NU_VISITANTE` int(11) DEFAULT NULL,
  `DATA_VISITA` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visita`
--

LOCK TABLES `visita` WRITE;
/*!40000 ALTER TABLE `visita` DISABLE KEYS */;
/*!40000 ALTER TABLE `visita` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visita_atividade`
--

DROP TABLE IF EXISTS `visita_atividade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `visita_atividade` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ATIVIDADE_ID` int(11) NOT NULL,
  `VISITA_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ATIVIDADE_ID` (`ATIVIDADE_ID`),
  KEY `VISITA_ID` (`VISITA_ID`),
  CONSTRAINT `visita_atividade_ibfk_1` FOREIGN KEY (`ATIVIDADE_ID`) REFERENCES `atividade` (`ID`),
  CONSTRAINT `visita_atividade_ibfk_2` FOREIGN KEY (`VISITA_ID`) REFERENCES `visita` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visita_atividade`
--

LOCK TABLES `visita_atividade` WRITE;
/*!40000 ALTER TABLE `visita_atividade` DISABLE KEYS */;
/*!40000 ALTER TABLE `visita_atividade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visitante`
--

DROP TABLE IF EXISTS `visitante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `visitante` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOME` varchar(45) DEFAULT NULL,
  `SOBRENOME` varchar(45) DEFAULT NULL,
  `ENDERECO_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ENDERECO_ID` (`ENDERECO_ID`),
  CONSTRAINT `visitante_ibfk_1` FOREIGN KEY (`ENDERECO_ID`) REFERENCES `endereco` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visitante`
--

LOCK TABLES `visitante` WRITE;
/*!40000 ALTER TABLE `visitante` DISABLE KEYS */;
/*!40000 ALTER TABLE `visitante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zoologico`
--

DROP TABLE IF EXISTS `zoologico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zoologico` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NU_CNPJ` varchar(14) DEFAULT NULL,
  `NOME` varchar(100) DEFAULT NULL,
  `ENDERECO_ID` int(11) NOT NULL,
  `FORNECEDOR_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ENDERECO_ID` (`ENDERECO_ID`),
  KEY `FORNECEDOR_ID` (`FORNECEDOR_ID`),
  CONSTRAINT `zoologico_ibfk_1` FOREIGN KEY (`ENDERECO_ID`) REFERENCES `endereco` (`ID`),
  CONSTRAINT `zoologico_ibfk_2` FOREIGN KEY (`FORNECEDOR_ID`) REFERENCES `fornecedor` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zoologico`
--

LOCK TABLES `zoologico` WRITE;
/*!40000 ALTER TABLE `zoologico` DISABLE KEYS */;
/*!40000 ALTER TABLE `zoologico` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-15 16:12:30
