if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[FK_gr_relation_groups]') and OBJECTPROPERTY(id, 

N'IsForeignKey') = 1)
ALTER TABLE [dbo].[gr_relation] DROP CONSTRAINT FK_gr_relation_groups
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[FK951176D07A8CB861]') and OBJECTPROPERTY(id, 

N'IsForeignKey') = 1)
ALTER TABLE [dbo].[gr_relation] DROP CONSTRAINT FK951176D07A8CB861
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[FK_ug_relation_groups]') and OBJECTPROPERTY(id, 

N'IsForeignKey') = 1)
ALTER TABLE [dbo].[ug_relation] DROP CONSTRAINT FK_ug_relation_groups
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[FK3A6B61097A8CB861]') and OBJECTPROPERTY(id, 

N'IsForeignKey') = 1)
ALTER TABLE [dbo].[ug_relation] DROP CONSTRAINT FK3A6B61097A8CB861
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[FK_gr_relation_roles]') and OBJECTPROPERTY(id, 

N'IsForeignKey') = 1)
ALTER TABLE [dbo].[gr_relation] DROP CONSTRAINT FK_gr_relation_roles
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[FK951176D0ABCC35BB]') and OBJECTPROPERTY(id, 

N'IsForeignKey') = 1)
ALTER TABLE [dbo].[gr_relation] DROP CONSTRAINT FK951176D0ABCC35BB
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[FK_operations_roles]') and OBJECTPROPERTY(id, 

N'IsForeignKey') = 1)
ALTER TABLE [dbo].[operations] DROP CONSTRAINT FK_operations_roles
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[FK3FD7ECABCC35BB]') and OBJECTPROPERTY(id, 

N'IsForeignKey') = 1)
ALTER TABLE [dbo].[operations] DROP CONSTRAINT FK3FD7ECABCC35BB
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[FK_ur_relation_roles]') and OBJECTPROPERTY(id, 

N'IsForeignKey') = 1)
ALTER TABLE [dbo].[ur_relation] DROP CONSTRAINT FK_ur_relation_roles
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[FKB9933D5EE2F6A6B7]') and OBJECTPROPERTY(id, 

N'IsForeignKey') = 1)
ALTER TABLE [dbo].[ur_relation] DROP CONSTRAINT FKB9933D5EE2F6A6B7
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[FK_answers_users]') and OBJECTPROPERTY(id, 

N'IsForeignKey') = 1)
ALTER TABLE [dbo].[answers] DROP CONSTRAINT FK_answers_users
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[FK_questions_users]') and OBJECTPROPERTY(id, 

N'IsForeignKey') = 1)
ALTER TABLE [dbo].[questions] DROP CONSTRAINT FK_questions_users
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[FK_ug_relation_users]') and OBJECTPROPERTY(id, 

N'IsForeignKey') = 1)
ALTER TABLE [dbo].[ug_relation] DROP CONSTRAINT FK_ug_relation_users
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[FK3A6B6109B14C1F1B]') and OBJECTPROPERTY(id, 

N'IsForeignKey') = 1)
ALTER TABLE [dbo].[ug_relation] DROP CONSTRAINT FK3A6B6109B14C1F1B
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[FK_ur_relation_users]') and OBJECTPROPERTY(id, 

N'IsForeignKey') = 1)
ALTER TABLE [dbo].[ur_relation] DROP CONSTRAINT FK_ur_relation_users
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[FKB9933D5EE322B145]') and OBJECTPROPERTY(id, 

N'IsForeignKey') = 1)
ALTER TABLE [dbo].[ur_relation] DROP CONSTRAINT FKB9933D5EE322B145
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[gr_relation]') and OBJECTPROPERTY(id, N'IsUserTable') 

= 1)
drop table [dbo].[gr_relation]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[groups]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[groups]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[operations]') and OBJECTPROPERTY(id, N'IsUserTable') = 

1)
drop table [dbo].[operations]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[roles]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[roles]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[ug_relation]') and OBJECTPROPERTY(id, N'IsUserTable') 

= 1)
drop table [dbo].[ug_relation]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[ur_relation]') and OBJECTPROPERTY(id, N'IsUserTable') 

= 1)
drop table [dbo].[ur_relation]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[users]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[users]
GO

CREATE TABLE [dbo].[gr_relation] (
	[grid] [int] IDENTITY (1, 1) PRIMARY KEY ,
	[gid] [int] NOT NULL ,
	[rid] [int] NOT NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[groups] (
	[gid] [int] IDENTITY (1, 1) PRIMARY KEY ,
	[gname] [nvarchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[gdescription] [nvarchar] (200) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[operations] (
	[optid] [int] IDENTITY (1, 1) PRIMARY KEY ,
	[optname] [nvarchar] (100) COLLATE Chinese_PRC_CI_AS NULL ,
	[rid] [int] NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[roles] (
	[rid] [int] IDENTITY (1, 1) PRIMARY KEY ,
	[rname] [nvarchar] (50) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[rdescription] [nvarchar] (200) COLLATE Chinese_PRC_CI_AS NULL ,
	[optdate] [datetime] NULL ,
	[comments] [nvarchar] (200) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[ug_relation] (
	[ugid] [int] IDENTITY (1, 1) PRIMARY KEY ,
	[uid] [int] NOT NULL ,
	[gid] [int] NOT NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[ur_relation] (
	[urid] [int] IDENTITY (1, 1) PRIMARY KEY ,
	[uid] [int] NOT NULL ,
	[rid] [int] NOT NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[users] (
	[uid] [int] IDENTITY (1, 1) PRIMARY KEY ,
	[username] [nvarchar] (50) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[realname] [nvarchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[password] [nvarchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[sex] [nvarchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[email] [nvarchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[address] [nvarchar] (150) COLLATE Chinese_PRC_CI_AS NULL ,
	[birthday] [smalldatetime] NULL ,
	[optdate] [datetime] NULL ,
	[comments] [nvarchar] (200) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
GO


ALTER TABLE gr_relation ADD CONSTRAINT fk_gr_g
FOREIGN KEY (gid) 
REFERENCES groups(gid);

ALTER TABLE gr_relation ADD CONSTRAINT fk_gr_r
FOREIGN KEY (rid) 
REFERENCES roles(rid);

ALTER TABLE operations ADD CONSTRAINT fk_o_r
FOREIGN KEY (rid) 
REFERENCES roles(rid);

ALTER TABLE ug_relation ADD CONSTRAINT fk_ug_u
FOREIGN KEY (uid) 
REFERENCES users(uid);

ALTER TABLE ug_relation ADD CONSTRAINT fk_ug_g
FOREIGN KEY (gid) 
REFERENCES groups(gid);

ALTER TABLE ur_relation ADD CONSTRAINT fk_ur_u
FOREIGN KEY (uid) 
REFERENCES users(uid);

ALTER TABLE ur_relation ADD CONSTRAINT fk_ur_r
FOREIGN KEY (rid) 
REFERENCES roles(rid);

