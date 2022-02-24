INSERT INTO gestionapp.empleados (id, apellidos, cod_zip, comentarios, direccion, email, imagen, movil, nombre, password, poblacion, telefono) VALUES (3, 'Empleado1', 1234, 'asdfas', 'Empleado1', 'asdasf@asdasfd.es', '', 1234567, 'Empleado1', '123445677', 'Empleado1', 12345678);
INSERT INTO gestionapp.empleados (id, apellidos, cod_zip, comentarios, direccion, email, imagen, movil, nombre, password, poblacion, telefono) VALUES (4, 'Empleado2', 1234, 'Empleado2', 'Empleado2', 'Empleado2@gmail.com', '', 12312312, 'Empleado2', 'Empleado2', 'Empleado2', 1234567);

INSERT INTO gestionapp.clientes (id, apellidos, cod_zip, comentarios, direccion, email, imagen, movil, nombre, pais, poblacion, telefono) VALUES (5, 'Cliente1', 1234, 'asdas', 'Cliente1', 'Cliente1@gmail.com', '', 12312312, 'Cliente1', 'Cliente1', 'Cliente1', 4546485);
INSERT INTO gestionapp.clientes (id, apellidos, cod_zip, comentarios, direccion, email, imagen, movil, nombre, pais, poblacion, telefono) VALUES (6, 'Cliente2', 1234, 'Cliente2', 'Cliente2', 'Cliente2@gmail.com', '', 12312312, 'Cliente2', 'Cliente2', 'Cliente2', 4546485);

INSERT INTO gestionapp.proveedores (id, cod_proveedor, comentarios, contacto, direccion, email, imagen, nif_cif, nombre, pais) VALUES (7, 1, '12312', 123123, 'Proveedor1', 'asfasfas@gmail.com', '', 123123, 'Proveedor1', 'Proveedor1');
INSERT INTO gestionapp.proveedores (id, cod_proveedor, comentarios, contacto, direccion, email, imagen, nif_cif, nombre, pais) VALUES (8, 2, 'Proveedor2', 1111111, 'Proveedor2', 'Proveedor2@gmail.com', '', 11111111, 'Proveedor2', 'Proveedor2');

INSERT INTO gestionapp.categorias (id, descripcion, nombre) VALUES (1, 'Categoria1', 'Categoria1');
INSERT INTO gestionapp.categorias (id, descripcion, nombre) VALUES (2, 'Categoria2', 'Categoria2');

INSERT INTO gestionapp.productos (id, descripcion, imagen, nombre, precio_compra, precio_venta, stock, categorias_id, proveedores_id) VALUES (9, 'asdasd', '', 'Producto1', 1, 2, 1, 1, 7);
INSERT INTO gestionapp.productos (id, descripcion, imagen, nombre, precio_compra, precio_venta, stock, categorias_id, proveedores_id) VALUES (10, 'asfdasfds', '', 'Producto2', 2, 4, 4, 2, 8);





