/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.application.controll.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ruan
 */
public class InitializerDB {
    
    public static void createTables() throws SQLException {
        
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:controllDB.db");
            Statement stmt = conn.createStatement();) {
            
            String sql = """
                         CREATE TABLE IF NOT EXISTS Salario (
                             id_salario VARCHAR(36) PRIMARY KEY NOT NULL,
                             salario NUMERIC(9,2) NOT NULL,
                             beneficio NUMERIC(9,2) DEFAULT 0.0,
                             renda_extra NUMERIC(9,2) DEFAULT 0.0
                         );
                         
                         CREATE TABLE IF NOT EXISTS Categoria (
                             id_categoria VARCHAR(36) PRIMARY KEY NOT NULL,
                             categoria VARCHAR(30) UNIQUE NOT NULL
                         );
                         
                         CREATE TABLE IF NOT EXISTS Imposto (
                             id_imposto VARCHAR(36) PRIMARY KEY NOT NULL,
                             nome VARCHAR(50) UNIQUE NOT NULL,
                             aliquota NUMERIC(9,2) NOT NULL
                         );
                         
                         CREATE TABLE IF NOT EXISTS Funcionario (
                             id_funcionario VARCHAR(36) PRIMARY KEY NOT NULL,
                             nome VARCHAR(100) NOT NULL,
                             cpf CHAR(11) UNIQUE NOT NULL,
                             email VARCHAR(120) UNIQUE NOT NULL,
                             endereco TEXT NOT NULL,
                             cargo VARCHAR(30) NOT NULL,
                             carga_horaria TEXT NOT NULL,
                             id_salario VARCHAR(36) NOT NULL,
                             FOREIGN KEY (id_salario) REFERENCES Salario (id_salario)
                         );
                         
                         CREATE TABLE IF NOT EXISTS Cliente (
                             id_cliente VARCHAR(36) PRIMARY KEY NOT NULL,
                             nome VARCHAR(120) NOT NULL,
                             telefone VARCHAR(30) UNIQUE NOT NULL,
                             email VARCHAR(120) UNIQUE NOT NULL,
                             cpf CHAR(11) UNIQUE DEFAULT NULL,
                             cnpj CHAR(14) UNIQUE DEFAULT NULL
                         );
                         
                         INSERT INTO Cliente (id_cliente, nome, telefone, email, cpf, cnpj) 
                         VALUES ('00000000-0000-0000-0000-000000000000', 'CONSUMIDOR PADRÃO', '000000000', 'consumidor@padrao.com', '00000000000', '00000000000000')
                         ON CONFLICT (id_cliente) DO NOTHING;
                         
                         CREATE TABLE IF NOT EXISTS Estoque (
                             id_produto VARCHAR(36) PRIMARY KEY NOT NULL,
                             codigo_interno VARCHAR(50) UNIQUE,
                             nome VARCHAR(200) NOT NULL,
                             descricao TEXT DEFAULT NULL,
                             quantidade INTEGER NOT NULL,
                             preco_aquisicao NUMERIC(9,2) NOT NULL,
                             preco_min_venda NUMERIC(9,2) NOT NULL,
                             preco_venda NUMERIC(9,2) NOT NULL,
                             lucro_minimo NUMERIC(9,2) NOT NULL,
                             lucro NUMERIC(9,2) NOT NULL,
                             status VARCHAR(7) NOT NULL,
                             id_categoria VARCHAR(36) NOT NULL,
                             id_funcionario VARCHAR(36) NOT NULL,
                             FOREIGN KEY (id_categoria) REFERENCES Categoria (id_categoria),
                             FOREIGN KEY (id_funcionario) REFERENCES Funcionario (id_funcionario)
                         );
                         
                         CREATE TABLE IF NOT EXISTS Estoque_Imposto (
                             id_produto VARCHAR(36) NOT NULL,
                             id_imposto VARCHAR(36) NOT NULL,
                             PRIMARY KEY (id_produto, id_imposto),
                             FOREIGN KEY (id_produto) REFERENCES Estoque (id_produto),
                             FOREIGN KEY (id_imposto) REFERENCES Imposto (id_imposto) -- CORRIGIDO AQUI
                         );
                         
                         CREATE TABLE IF NOT EXISTS Venda (
                             id_venda VARCHAR(36) PRIMARY KEY NOT NULL,
                             valor_total NUMERIC(9,2) NOT NULL,
                             status_pagamento VARCHAR(20) NOT NULL,
                             data_venda TIMESTAMP NOT NULL,
                             id_cliente VARCHAR(36) NOT NULL,
                             id_funcionario VARCHAR(36) NOT NULL,
                             FOREIGN KEY (id_cliente) REFERENCES Cliente (id_cliente),
                             FOREIGN KEY (id_funcionario) REFERENCES Funcionario (id_funcionario)
                         );
                         
                         CREATE TABLE IF NOT EXISTS Item_Venda (
                             id_item VARCHAR(36) PRIMARY KEY NOT NULL,
                             quantidade INTEGER NOT NULL,
                             preco_unitario_no_ato NUMERIC(9,2) NOT NULL,
                             id_venda VARCHAR(36) NOT NULL,
                             id_produto VARCHAR(36) NOT NULL,
                             FOREIGN KEY (id_venda) REFERENCES Venda (id_venda),
                             FOREIGN KEY (id_produto) REFERENCES Estoque (id_produto)
                         );
                         
                         CREATE TABLE IF NOT EXISTS Caixa (
                             id_caixa VARCHAR(36) PRIMARY KEY NOT NULL,
                             mes_referencia VARCHAR(20) NOT NULL,
                             valor_caixa_final NUMERIC(9,2) NOT NULL,
                             descricao TEXT DEFAULT NULL,
                             data_fechamento TIMESTAMP NOT NULL,
                             id_funcionario VARCHAR(36) NOT NULL,
                             FOREIGN KEY (id_funcionario) REFERENCES Funcionario (id_funcionario)
                         );
                         
                         CREATE TABLE IF NOT EXISTS Gasto (
                             id_gasto VARCHAR(36) PRIMARY KEY NOT NULL,
                             valor_gasto NUMERIC(9,2) NOT NULL,
                             descricao TEXT DEFAULT NULL,
                             categoria VARCHAR(50) NOT NULL,
                             data TIMESTAMP NOT NULL,
                             id_funcionario VARCHAR(36) NOT NULL,
                             FOREIGN KEY (id_funcionario) REFERENCES Funcionario (id_funcionario)
                         );
                         
                         CREATE TABLE IF NOT EXISTS Historico (
                             id_historico VARCHAR(36) PRIMARY KEY NOT NULL,
                             descricao_acao TEXT DEFAULT NULL,
                             horario TIMESTAMP NOT NULL,
                             id_funcionario VARCHAR(36) NOT NULL,
                             FOREIGN KEY (id_funcionario) REFERENCES Funcionario (id_funcionario)
                         );
                     """;
            
            stmt.execute(sql);
            System.out.println("Tabelas criadas com sucesso!");
            
        } catch (SQLException exception) {
            System.err.println("Cannot Create Table Because => " + exception.getMessage());
        }
    }
}
