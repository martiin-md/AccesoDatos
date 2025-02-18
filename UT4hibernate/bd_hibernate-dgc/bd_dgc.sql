-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-01-2025 a las 18:45:22
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bd_dgc`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alegaciones`
--

CREATE TABLE `alegaciones` (
  `Id_Alegacion` int(11) NOT NULL,
  `Id_Usuario` int(11) NOT NULL,
  `Id_Proyecto` int(11) NOT NULL,
  `Fecha_Presentada` date DEFAULT NULL,
  `Descripcion` tinytext DEFAULT NULL,
  `Codigo_Validacion` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `alegaciones`
--

INSERT INTO `alegaciones` (`Id_Alegacion`, `Id_Usuario`, `Id_Proyecto`, `Fecha_Presentada`, `Descripcion`, `Codigo_Validacion`) VALUES
(1, 3, 1, '2025-01-20', 'rtewqwer', '654128'),
(2, 3, 1, '2025-01-19', 'otra alegacion', '656421'),
(3, 12, 3, '2025-01-23', 'Otro ejemplo', '123434');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `favoritos`
--

CREATE TABLE `favoritos` (
  `Id_Favorito` int(11) NOT NULL,
  `Id_Usuario` int(11) NOT NULL,
  `Id_Proyecto` int(11) NOT NULL,
  `Fecha_Registro` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `favoritos`
--

INSERT INTO `favoritos` (`Id_Favorito`, `Id_Usuario`, `Id_Proyecto`, `Fecha_Registro`) VALUES
(1, 3, 1, '2025-01-19'),
(2, 3, 1, '2025-01-19'),
(3, 12, 2, '2025-01-23');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `informes`
--

CREATE TABLE `informes` (
  `Id_Informe` int(11) NOT NULL,
  `Id_Alegacion` int(11) NOT NULL,
  `Tipo_Informacion` varchar(50) DEFAULT NULL,
  `Enlace_Descarga` varchar(255) DEFAULT NULL,
  `Fecha_Registro` date DEFAULT NULL,
  `Id_Usuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `informes`
--

INSERT INTO `informes` (`Id_Informe`, `Id_Alegacion`, `Tipo_Informacion`, `Enlace_Descarga`, `Fecha_Registro`, `Id_Usuario`) VALUES
(1, 1, 'Seguro Personal', NULL, '2025-01-19', 3),
(2, 1, 'Permiso de Conducción', NULL, '2025-01-19', 3),
(3, 1, 'Vehículos ', '', '2025-01-19', 3),
(4, 2, ' Multas', NULL, '2025-01-20', 3),
(5, 2, 'Seguro Coche', NULL, '2025-01-23', 11),
(6, 1, 'Puntos Perdidos', 'https://servidor.com/descargas/informes/informe_3_1_2025-01-23.pdf', '2025-01-23', 3),
(7, 2, 'Vehiculos Vendidos', 'https://servidor.com/descargas/informes/informe_7_2_2025-01-23.pdf', '2025-01-23', 7),
(8, 1, 'Permisos Caducados', 'https://servidor.com/descargas/informes/informe_6_1_2025-01-23.pdf', '2025-01-23', 6),
(9, 2, 'Vehiculos Lujosos', 'https://servidor.com/descargas/informes/informe_6_2_2025-01-23.pdf', '2025-01-23', 6),
(10, 1, 'Vehiculos Comprados en 2025', 'https://servidor.com/descargas/informes/informe_3_1_2025-01-23.pdf', '2025-01-23', 3),
(11, 1, 'Informe 99', 'https://servidor.com/descargas/informes/informe_3_1_2025-01-23.pdf', '2025-01-23', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proyectos`
--

CREATE TABLE `proyectos` (
  `Id_Proyecto` int(11) NOT NULL,
  `Id_Usuario` int(11) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `Descripcion` tinytext DEFAULT NULL,
  `Fecha_Inicio` date NOT NULL,
  `Fecha_Fin` date DEFAULT NULL,
  `Ubicacion` varchar(255) DEFAULT NULL,
  `Creador_Responsable` varchar(100) DEFAULT NULL,
  `Fase` varchar(255) NOT NULL,
  `Estado` tinytext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `proyectos`
--

INSERT INTO `proyectos` (`Id_Proyecto`, `Id_Usuario`, `Nombre`, `Descripcion`, `Fecha_Inicio`, `Fecha_Fin`, `Ubicacion`, `Creador_Responsable`, `Fase`, `Estado`) VALUES
(1, 3, 'proyecto shopify', 'negocio dropshipping ', '2025-01-19', '2025-06-12', 'C/ Calle 1', 'martin', 'Planificación', NULL),
(2, 3, 'bd_dgc', 'proyecto dgc', '2025-01-20', '2025-01-24', 'C/ Era Pareja', 'martin', 'terminado', NULL),
(3, 3, 'negocio martin al limite', 'hotel 4 estrellas', '2021-10-20', '2025-01-20', 'C/oxford street-London', 'martin', 'terminado', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `Id_Usuario` int(11) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `Apellidos` varchar(150) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Dni` varchar(8) DEFAULT NULL,
  `Telefono` varchar(10) DEFAULT NULL,
  `Tipo_Usuario` tinytext NOT NULL,
  `Contrasena` varchar(255) DEFAULT NULL,
  `Is_Validado` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`Id_Usuario`, `Nombre`, `Apellidos`, `Email`, `Dni`, `Telefono`, `Tipo_Usuario`, `Contrasena`, `Is_Validado`) VALUES
(3, 'martin', 'dimitrov', 'm@gmail.com', 'x7184393', '644313658', 'GestorDGC', 'M4RRT11N@2004', 1),
(4, 'andrea', 'martinez', 'andrea2@gmail.com', 'x7184393', '644313522', 'DepartamentoAdministraciones', 'Andr34e@2003', 1),
(6, 'roberto', 'lópez', 'robertolopez02@gmail.com', 'c8123572', '622579118', 'CiudadanoEmpresa', 'l0p3zr0b3rrT0', 1),
(7, 'francis', 'vanpin', 'francis@gmail.com', 'x8196453', '642118659', 'GestorDGC', 'vanpinfrancinsss', 1),
(11, 'duna', 'alvarez', 'dalvar3z@gmail.com', 'x9112397', '622812948', 'GestorDGC', 'alvarezd1u1n1a', 1),
(12, 'Martina', 'Nuñez', 'martinanunez003@gmail.com', '', '', 'CiudadanoEmpresa', 'M@RRT1NAnunez2003', 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alegaciones`
--
ALTER TABLE `alegaciones`
  ADD PRIMARY KEY (`Id_Alegacion`),
  ADD KEY `FKmla5hnkns3tkga3a91y0h6dqv` (`Id_Proyecto`),
  ADD KEY `FK7tx4vimpcnc15gv16ufm9735k` (`Id_Usuario`);

--
-- Indices de la tabla `favoritos`
--
ALTER TABLE `favoritos`
  ADD PRIMARY KEY (`Id_Favorito`),
  ADD KEY `FKpyol1538t1scmy3591vk9ia78` (`Id_Proyecto`),
  ADD KEY `FKmcerghvt3ul676ldj7s42mioj` (`Id_Usuario`);

--
-- Indices de la tabla `informes`
--
ALTER TABLE `informes`
  ADD PRIMARY KEY (`Id_Informe`),
  ADD KEY `FKka3yomb7jtn4i5401obwhbkin` (`Id_Alegacion`),
  ADD KEY `FK1jgpbehnx80bupgfrangixkl4` (`Id_Usuario`);

--
-- Indices de la tabla `proyectos`
--
ALTER TABLE `proyectos`
  ADD PRIMARY KEY (`Id_Proyecto`),
  ADD KEY `FK1gnuop4trqnl3i29pkhupm5r6` (`Id_Usuario`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`Id_Usuario`),
  ADD UNIQUE KEY `Email` (`Email`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alegaciones`
--
ALTER TABLE `alegaciones`
  MODIFY `Id_Alegacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `favoritos`
--
ALTER TABLE `favoritos`
  MODIFY `Id_Favorito` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `informes`
--
ALTER TABLE `informes`
  MODIFY `Id_Informe` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `proyectos`
--
ALTER TABLE `proyectos`
  MODIFY `Id_Proyecto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `Id_Usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `alegaciones`
--
ALTER TABLE `alegaciones`
  ADD CONSTRAINT `FK7tx4vimpcnc15gv16ufm9735k` FOREIGN KEY (`Id_Usuario`) REFERENCES `usuarios` (`Id_Usuario`),
  ADD CONSTRAINT `FKmla5hnkns3tkga3a91y0h6dqv` FOREIGN KEY (`Id_Proyecto`) REFERENCES `proyectos` (`Id_Proyecto`),
  ADD CONSTRAINT `alegaciones_ibfk_1` FOREIGN KEY (`Id_Usuario`) REFERENCES `usuarios` (`Id_Usuario`),
  ADD CONSTRAINT `alegaciones_ibfk_2` FOREIGN KEY (`Id_Proyecto`) REFERENCES `proyectos` (`Id_Proyecto`);

--
-- Filtros para la tabla `favoritos`
--
ALTER TABLE `favoritos`
  ADD CONSTRAINT `FKmcerghvt3ul676ldj7s42mioj` FOREIGN KEY (`Id_Usuario`) REFERENCES `usuarios` (`Id_Usuario`),
  ADD CONSTRAINT `FKpyol1538t1scmy3591vk9ia78` FOREIGN KEY (`Id_Proyecto`) REFERENCES `proyectos` (`Id_Proyecto`),
  ADD CONSTRAINT `favoritos_ibfk_1` FOREIGN KEY (`Id_Usuario`) REFERENCES `usuarios` (`Id_Usuario`),
  ADD CONSTRAINT `favoritos_ibfk_2` FOREIGN KEY (`Id_Proyecto`) REFERENCES `proyectos` (`Id_Proyecto`);

--
-- Filtros para la tabla `informes`
--
ALTER TABLE `informes`
  ADD CONSTRAINT `FK1jgpbehnx80bupgfrangixkl4` FOREIGN KEY (`Id_Usuario`) REFERENCES `usuarios` (`Id_Usuario`),
  ADD CONSTRAINT `FKka3yomb7jtn4i5401obwhbkin` FOREIGN KEY (`Id_Alegacion`) REFERENCES `alegaciones` (`Id_Alegacion`),
  ADD CONSTRAINT `informes_ibfk_1` FOREIGN KEY (`Id_Alegacion`) REFERENCES `alegaciones` (`Id_Alegacion`);

--
-- Filtros para la tabla `proyectos`
--
ALTER TABLE `proyectos`
  ADD CONSTRAINT `FK1gnuop4trqnl3i29pkhupm5r6` FOREIGN KEY (`Id_Usuario`) REFERENCES `usuarios` (`Id_Usuario`),
  ADD CONSTRAINT `proyectos_ibfk_1` FOREIGN KEY (`Id_Usuario`) REFERENCES `usuarios` (`Id_Usuario`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
