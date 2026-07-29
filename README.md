# Fase-Um-FIAP

## O problema 
Na nossa região, um grupo de restaurantes decidiu contratar estudantes para construir um sistema de gestão para seus estabelecimentos. Essa decisão foi motivada pelo alto custo de sistemas individuais, o que levou os restaurantes a se unirem para desenvolver um sistema único e compartilhado. Esse sistema permitirá que os clientes escolham restaurantes com base na comida oferecida, em vez de se basearem na qualidade do sistema de gestão. 
O objetivo é criar um sistema robusto que permita a todos os restaurantes gerenciar eficientemente suas operações, enquanto os clientes poderão consultar informações, deixar avaliações e fazer pedidos online. 
Devido à limitação de recursos financeiros, foi acordado que a entrega do sistema será realizada em fases, garantindo que cada etapa seja desenvolvida de forma cuidadosa e eficaz. 
A divisão em fases possibilitará uma implementação gradual e controlada, permitindo ajustes e melhorias contínuas conforme o sistema for sendo utilizado e avaliado tanto pelos restaurantes quanto pelos clientes.

## Objetivo 
Desenvolver um backend completo e robusto utilizando Spring Boot e os princípios aprendidos na Fase 1 do curso. 
O sistema deve permitir: 
- Cadastro, atualização e exclusão de usuários; 
- Troca de senha do usuário em endpoint separado; 
- Atualização das demais informações do usuário em endpoint distinto do endpoint de senha; 
- Registro da data da última alteração; 
- Busca de usuários pelo nome; 
- Garantia de que o e-mail cadastrado seja único; 
- Validação de login obrigatória, por meio de um serviço que verifique se login e senha são válidos: 
- - o Não é obrigatório utilizar Spring Security; 
- - o Pode ser utilizada uma validação simples consultando os dados no banco. 

A aplicação deverá ser dockerizada, utilizando Docker Compose para orquestração junto com um banco de dados relacional (MySQL ou PostgreSQL). 
Usuários 
O sistema deve obrigatoriamente contemplar dois tipos de usuário: 
- Dono de restaurante; 
- Cliente. 

Além desses, outros tipos de usuários poderão ser adicionados, caso o grupo considere necessário para enriquecer o modelo. 
Campos obrigatórios para qualquer usuário: 
- Nome (String); 
- E-mail (String, único); 
- Login (String); 
- Senha (String); 
- Data da última alteração (Date); 
- Endereço (String ou objeto com atributos como rua, número, cidade, CEP).
