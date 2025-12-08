module Production {
	// JavaFX
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.base;

	// JDBC
	requires java.sql;

	// Abre pacotes para reflexão (necessário para JavaFX e JDBC)
	opens main;
	opens main.entities;
	opens main.service;
	opens main.dao;
	opens main.persistence;
	opens main.view;
}