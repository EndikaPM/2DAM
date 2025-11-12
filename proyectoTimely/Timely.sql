-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 12-11-2025 a las 09:54:31
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `Timely`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `departamento`
--

CREATE TABLE `departamento` (
  `id` int(10) UNSIGNED NOT NULL,
  `nombre` varchar(80) NOT NULL,
  `id_empresa` char(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresa`
--

CREATE TABLE `empresa` (
  `nif` char(12) NOT NULL,
  `nombre` varchar(80) NOT NULL,
  `direccion` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `horas_trabajo`
--

CREATE TABLE `horas_trabajo` (
  `id_usuario` char(9) NOT NULL,
  `horas_contrato` smallint(5) UNSIGNED NOT NULL,
  `horas_trabajadas` smallint(5) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `jornada`
--

CREATE TABLE `jornada` (
  `id` int(10) UNSIGNED NOT NULL,
  `id_trabajador` char(9) NOT NULL,
  `fecha_actual` date NOT NULL,
  `hora_entrada` time NOT NULL,
  `hora_salida` time NOT NULL,
  `modificado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `dni` char(9) NOT NULL,
  `firstName` varchar(50) NOT NULL,
  `lastName` varchar(150) NOT NULL,
  `email` varchar(250) NOT NULL,
  `password` varchar(50) NOT NULL,
  `birthday` date NOT NULL,
  `contract_date` date NOT NULL,
  `social_security` varchar(15) NOT NULL,
  `user_type` enum('Administrador','Empleado','Jefe') NOT NULL,
  `department` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `departamento`
--
ALTER TABLE `departamento`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `empresa`
--
ALTER TABLE `empresa`
  ADD PRIMARY KEY (`nif`);

--
-- Indices de la tabla `horas_trabajo`
--
ALTER TABLE `horas_trabajo`
  ADD PRIMARY KEY (`id_usuario`);

--
-- Indices de la tabla `jornada`
--
ALTER TABLE `jornada`
  ADD PRIMARY KEY (`id`,`id_trabajador`),
  ADD KEY `fk_usuario` (`id_trabajador`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`dni`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `departamento`
--
ALTER TABLE `departamento`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `jornada`
--
ALTER TABLE `jornada`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `horas_trabajo`
--
ALTER TABLE `horas_trabajo`
  ADD CONSTRAINT `fk_usuario_horas` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`dni`);

--
-- Filtros para la tabla `jornada`
--
ALTER TABLE `jornada`
  ADD CONSTRAINT `fk_usuario` FOREIGN KEY (`id_trabajador`) REFERENCES `usuario` (`dni`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
