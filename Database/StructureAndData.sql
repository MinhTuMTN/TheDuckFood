/*
 Navicat Premium Data Transfer

 Source Server         : MINHTU_SERVER
 Source Server Type    : SQL Server
 Source Server Version : 15002095
 Source Host           : MINHTUNGUYEN\MINHTU_SERVER:1433
 Source Catalog        : TheDuckFood
 Source Schema         : dbo

 Target Server Type    : SQL Server
 Target Server Version : 15002095
 File Encoding         : 65001

 Date: 19/05/2023 15:59:18
*/


-- ----------------------------
-- Table structure for coupon
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[coupon]') AND type IN ('U'))
	DROP TABLE [dbo].[coupon]
GO

CREATE TABLE [dbo].[coupon] (
  [coupon_id] int  IDENTITY(1,1) NOT NULL,
  [amount] int  NOT NULL,
  [coupon_code] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [created_at] datetime2(7)  NULL,
  [max_discount] float(53)  NULL,
  [min_price] float(53)  NULL,
  [status] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [updated_at] datetime2(7)  NULL,
  [used] int  NOT NULL,
  [store_store_id] bigint  NULL,
  [expired_at] datetime2(7)  NULL,
  [discount] float(53)  NOT NULL,
  [start_at] datetime2(7)  NULL
)
GO

ALTER TABLE [dbo].[coupon] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of coupon
-- ----------------------------
SET IDENTITY_INSERT [dbo].[coupon] ON
GO

INSERT INTO [dbo].[coupon] ([coupon_id], [amount], [coupon_code], [created_at], [max_discount], [min_price], [status], [updated_at], [used], [store_store_id], [expired_at], [discount], [start_at]) VALUES (N'3', N'100', N'HAPPY', N'2023-05-08 10:26:04.0000000', N'100000', N'10000', N'activated', N'2023-05-09 10:26:32.0000000', N'7', N'1', N'2023-05-10 12:14:12.0000000', N'0.5', N'2023-05-09 05:44:04.0000000')
GO

INSERT INTO [dbo].[coupon] ([coupon_id], [amount], [coupon_code], [created_at], [max_discount], [min_price], [status], [updated_at], [used], [store_store_id], [expired_at], [discount], [start_at]) VALUES (N'5', N'10', N'LAITHIEU', N'2023-05-13 12:46:36.0000000', N'50000', N'200000', N'activated', N'2023-05-13 12:46:57.0000000', N'5', N'18', N'2023-05-20 12:47:04.0000000', N'0.200000002980232', N'2023-05-13 13:00:00.0000000')
GO

INSERT INTO [dbo].[coupon] ([coupon_id], [amount], [coupon_code], [created_at], [max_discount], [min_price], [status], [updated_at], [used], [store_store_id], [expired_at], [discount], [start_at]) VALUES (N'6', N'100', N'MAYSALE', N'2023-05-14 01:24:43.5640000', N'50000', N'10000', N'activated', N'2023-05-14 01:24:43.5640000', N'2', N'18', N'2023-06-01 06:59:59.0000000', N'0.204999998211861', N'2023-05-15 07:00:00.0000000')
GO

INSERT INTO [dbo].[coupon] ([coupon_id], [amount], [coupon_code], [created_at], [max_discount], [min_price], [status], [updated_at], [used], [store_store_id], [expired_at], [discount], [start_at]) VALUES (N'7', N'100', N'MAYSALE1', N'2023-05-14 01:27:45.2040000', N'50000', N'10000', N'activated', N'2023-05-14 01:27:45.2040000', N'0', N'18', N'2023-05-16 06:59:59.0000000', N'0.5', N'2023-05-15 06:59:59.0000000')
GO

INSERT INTO [dbo].[coupon] ([coupon_id], [amount], [coupon_code], [created_at], [max_discount], [min_price], [status], [updated_at], [used], [store_store_id], [expired_at], [discount], [start_at]) VALUES (N'8', N'60', N'LAITHIEU2', N'2023-05-14 03:08:27.5840000', N'1000', N'100', N'activated', N'2023-05-14 03:08:27.5840000', N'60', N'18', N'2023-05-15 10:08:13.0000000', N'0.100000001490116', N'2023-05-14 10:08:11.0000000')
GO

INSERT INTO [dbo].[coupon] ([coupon_id], [amount], [coupon_code], [created_at], [max_discount], [min_price], [status], [updated_at], [used], [store_store_id], [expired_at], [discount], [start_at]) VALUES (N'9', N'2', N'LAITHIEU2', N'2023-05-14 03:15:51.5560000', N'10000', N'10000', N'activated', N'2023-05-14 03:15:51.5560000', N'2', N'18', N'2023-05-14 10:15:38.0000000', N'0.5', N'2023-05-14 10:15:36.0000000')
GO

INSERT INTO [dbo].[coupon] ([coupon_id], [amount], [coupon_code], [created_at], [max_discount], [min_price], [status], [updated_at], [used], [store_store_id], [expired_at], [discount], [start_at]) VALUES (N'10', N'50', N'KFCNGONGHE01', N'2023-05-16 23:19:24.1010000', N'25000', N'50000', N'activated', N'2023-05-16 23:19:24.1010000', N'9', N'3', N'2023-05-20 07:00:00.0000000', N'0.100000001490116', N'2023-05-15 07:00:00.0000000')
GO

INSERT INTO [dbo].[coupon] ([coupon_id], [amount], [coupon_code], [created_at], [max_discount], [min_price], [status], [updated_at], [used], [store_store_id], [expired_at], [discount], [start_at]) VALUES (N'11', N'1', N'NGONLAMKFC', N'2023-05-17 01:20:09.7790000', N'100000', N'50000', N'activated', N'2023-05-17 01:20:09.7790000', N'1', N'3', N'2023-05-20 07:00:00.0000000', N'0.200000002980232', N'2023-05-15 07:00:00.0000000')
GO

INSERT INTO [dbo].[coupon] ([coupon_id], [amount], [coupon_code], [created_at], [max_discount], [min_price], [status], [updated_at], [used], [store_store_id], [expired_at], [discount], [start_at]) VALUES (N'13', N'10', N'LAITHIEU1', N'2023-05-17 01:23:00.4550000', N'60000', N'1000000', N'activated', N'2023-05-17 01:23:00.4550000', N'0', N'18', N'2023-05-24 07:00:00.0000000', N'0.300000011920929', N'2023-05-17 07:00:00.0000000')
GO

INSERT INTO [dbo].[coupon] ([coupon_id], [amount], [coupon_code], [created_at], [max_discount], [min_price], [status], [updated_at], [used], [store_store_id], [expired_at], [discount], [start_at]) VALUES (N'17', N'1', N'KFCDONHE', N'2023-05-17 01:27:15.1610000', N'50000', N'100000', N'activated', N'2023-05-17 01:27:15.1610000', N'1', N'3', N'2023-05-30 07:00:00.0000000', N'0.100000001490116', N'2023-05-15 07:00:00.0000000')
GO

INSERT INTO [dbo].[coupon] ([coupon_id], [amount], [coupon_code], [created_at], [max_discount], [min_price], [status], [updated_at], [used], [store_store_id], [expired_at], [discount], [start_at]) VALUES (N'18', N'36', N'LAITHIEU2', N'2023-05-17 01:32:05.4350000', N'5000', N'600000', N'activated', N'2023-05-17 01:32:05.4350000', N'0', N'18', N'2023-05-23 07:00:00.0000000', N'0.300000011920929', N'2023-05-17 07:00:00.0000000')
GO

INSERT INTO [dbo].[coupon] ([coupon_id], [amount], [coupon_code], [created_at], [max_discount], [min_price], [status], [updated_at], [used], [store_store_id], [expired_at], [discount], [start_at]) VALUES (N'19', N'1', N'LAITHIEU3', N'2023-05-17 01:41:10.0380000', N'500', N'1000000', N'activated', N'2023-05-17 01:41:10.0380000', N'0', N'18', N'2023-05-18 07:00:00.0000000', N'0.100000001490116', N'2023-05-17 07:00:00.0000000')
GO

INSERT INTO [dbo].[coupon] ([coupon_id], [amount], [coupon_code], [created_at], [max_discount], [min_price], [status], [updated_at], [used], [store_store_id], [expired_at], [discount], [start_at]) VALUES (N'20', N'10', N'LAITHIEUNGONTUYET', N'2023-05-17 18:53:11.4700000', N'25000', N'100000', N'activated', N'2023-05-17 18:53:11.4700000', N'0', N'18', N'2023-05-30 07:00:00.0000000', N'0.100000001490116', N'2023-05-16 07:00:00.0000000')
GO

INSERT INTO [dbo].[coupon] ([coupon_id], [amount], [coupon_code], [created_at], [max_discount], [min_price], [status], [updated_at], [used], [store_store_id], [expired_at], [discount], [start_at]) VALUES (N'21', N'10', N'LAITHIEUCHAOHE', N'2023-05-17 19:06:53.4860000', N'20000', N'100000', N'activated', N'2023-05-17 19:06:53.4860000', N'0', N'18', N'2023-05-20 07:00:00.0000000', N'0.100000001490116', N'2023-05-15 07:00:00.0000000')
GO

INSERT INTO [dbo].[coupon] ([coupon_id], [amount], [coupon_code], [created_at], [max_discount], [min_price], [status], [updated_at], [used], [store_store_id], [expired_at], [discount], [start_at]) VALUES (N'22', N'10', N'LAITHIEU07', N'2023-05-18 10:18:33.8080000', N'20000', N'100000', N'activated', N'2023-05-18 10:18:33.8080000', N'0', N'18', N'2023-05-19 07:00:00.0000000', N'0.100000001490116', N'2023-05-17 07:00:00.0000000')
GO

SET IDENTITY_INSERT [dbo].[coupon] OFF
GO


-- ----------------------------
-- Table structure for delivery_man
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[delivery_man]') AND type IN ('U'))
	DROP TABLE [dbo].[delivery_man]
GO

CREATE TABLE [dbo].[delivery_man] (
  [delivery_man_id] bigint  IDENTITY(1,1) NOT NULL,
  [balance] float(53)  NULL,
  [created_at] datetime2(7)  NULL,
  [fcm_token] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [full_name] nvarchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [phone] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [status] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [updated_at] datetime2(7)  NULL
)
GO

ALTER TABLE [dbo].[delivery_man] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of delivery_man
-- ----------------------------
SET IDENTITY_INSERT [dbo].[delivery_man] ON
GO

INSERT INTO [dbo].[delivery_man] ([delivery_man_id], [balance], [created_at], [fcm_token], [full_name], [phone], [status], [updated_at]) VALUES (N'1', N'499876000', N'2023-05-08 16:44:50.0000000', NULL, N'Shipper', N'0123456789', N'online', N'2023-05-08 16:45:13.0000000')
GO

SET IDENTITY_INSERT [dbo].[delivery_man] OFF
GO


-- ----------------------------
-- Table structure for delivery_man_account
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[delivery_man_account]') AND type IN ('U'))
	DROP TABLE [dbo].[delivery_man_account]
GO

CREATE TABLE [dbo].[delivery_man_account] (
  [email] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [otp] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [otp_created_at] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [otp_last_attempt] datetime2(7)  NULL,
  [otp_wrong_count] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [password] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [status] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [updated_at] datetime2(7)  NULL,
  [delivery_man_id] bigint  NULL
)
GO

ALTER TABLE [dbo].[delivery_man_account] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of delivery_man_account
-- ----------------------------
INSERT INTO [dbo].[delivery_man_account] ([email], [otp], [otp_created_at], [otp_last_attempt], [otp_wrong_count], [password], [status], [updated_at], [delivery_man_id]) VALUES (N'shipper@gmail.com', NULL, NULL, NULL, NULL, N'PJkJr+wlNU1VHa4hWQuybjjVPyFzuNPcPu5MBH56scHri4UQPjvnumE7MbtcnDYhTcnxSkL9ei/bhIVrylxEwg==', N'activated', N'2023-05-16 10:56:35.0000000', N'1')
GO


-- ----------------------------
-- Table structure for food
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[food]') AND type IN ('U'))
	DROP TABLE [dbo].[food]
GO

CREATE TABLE [dbo].[food] (
  [food_id] bigint  IDENTITY(1,1) NOT NULL,
  [created_at] datetime2(7)  NULL,
  [description] nvarchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [food_name] nvarchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [image] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [price] float(53)  NOT NULL,
  [price_promotion] float(53)  NOT NULL,
  [status] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [updated_at] datetime2(7)  NULL,
  [store_store_id] bigint  NULL
)
GO

ALTER TABLE [dbo].[food] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of food
-- ----------------------------
SET IDENTITY_INSERT [dbo].[food] ON
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'1', N'2017-04-03 12:07:10.0000000', N'To clear or reload various internal caches, flush tables, or acquire locks, control-click your connection in the Navigation pane and select Flush and choose the flush option. You must have the reload ', N'Dessert', N'C:\Users\Administrator\Pictures\img_364496.png', N'151050', N'151050', N'selling', N'2018-12-07 05:02:29.0000000', N'15')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'2', N'2021-11-29 03:30:16.0000000', N'Gồm: sườn, bì, chả trứng chiên', N'Cơm sườn 1', N'1684295850829.jpg', N'40000', N'40000', N'selling', N'2000-12-09 06:31:34.0000000', N'18')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'3', N'2012-10-18 03:09:47.0000000', N'To start working with your server in Navicat, you should first establish a connection or several connections using the Connection window. You will succeed because most people are lazy.', N'omni-Pasta Recipes', N'C:\Users\Administrator\Pictures\img_844939.jpg', N'164163', N'164163', N'selling', N'2008-06-22 04:32:59.0000000', N'17')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'4', N'2001-02-15 22:35:02.0000000', N'If opportunity doesn’t knock, build a door. To start working with your server in Navicat, you should first establish a connection or several connections using the Connection window.', N'omni-Cake', N'C:\Users\Administrator\Pictures\img_033022.jpg', N'74511', N'74511', N'selling', N'2014-10-29 05:26:44.0000000', N'12')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'5', N'2021-08-24 10:34:08.0000000', N'Typically, it is employed as an encrypted version of Telnet. In the middle of winter I at last discovered that there was in me an invincible summer. After logged in the Navicat Cloud feature, the Navi', N'xancakes se', N'C:\Users\Administrator\Pictures\img_685735.png', N'193739', N'193739', N'selling', N'2005-01-04 01:33:50.0000000', N'25')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'6', N'2000-02-24 22:48:51.0000000', N'A man’s best friends are his ten fingers. Navicat allows you to transfer data from one database and/or schema to another with detailed analytical process. The first step is as good as half over.', N'Chicken Biryani core', N'C:\Users\Administrator\Pictures\img_226590.png', N'164732', N'164732', N'selling', N'2010-10-24 20:50:43.0000000', N'25')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'7', N'2014-07-21 00:54:58.0000000', N'A query is used to extract data from the database in a readable format according to the user''s request. To successfully establish a new connection to local/remote server - no matter via SSL or SSH, se', N'Ramen pi', N'C:\Users\Administrator\Pictures\img_906786.jpg', N'18132', N'18132', N'selling', N'2009-03-07 04:17:02.0000000', N'22')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'8', N'2009-06-23 16:32:27.0000000', N'Remember that failure is an event, not a person. It provides strong authentication and secure encrypted communications between two hosts, known as SSH Port Forwarding (Tunneling), over an insecure net', N'Hot Dog', N'C:\Users\Administrator\Pictures\img_540519.png', N'150001', N'150001', N'selling', N'2014-10-15 07:37:24.0000000', N'2')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'9', N'2012-09-11 08:16:48.0000000', N'3 miếng đùi gà', N'Gà Rán (3 miếng)', N'1684249542859.jpg', N'105000', N'105000', N'selling', N'2008-06-05 21:05:32.0000000', N'3')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'10', N'2017-08-05 04:05:56.0000000', N'A query is used to extract data from the database in a readable format according to the user''s request. If your Internet Service Provider (ISP) does not provide direct access to its server, Secure Tun', N'aancakes', N'C:\Users\Administrator\Pictures\img_837603.jpg', N'147265', N'147265', N'selling', N'2006-04-06 05:34:28.0000000', N'4')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'11', N'2010-02-11 10:13:31.0000000', N'The reason why a great man is great is that he resolves to be a great man. All the Navicat Cloud objects are located under different projects. You can share the project to other Navicat Cloud accounts', N'Burger Tôm', N'1684250476957.jpg', N'45000', N'45000', N'selling', N'2012-05-24 07:33:03.0000000', N'3')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'12', N'2019-11-05 06:54:21.0000000', N'Sinh tố bơ sữa', N'Sinh tố trái cây - Bơ', N'1684291653816.jpg', N'35000', N'35000', N'selling', N'2000-04-01 09:30:09.0000000', N'14')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'13', N'2011-04-29 01:34:38.0000000', N'Optimism is the one quality more associated with success and happiness than any other. To get a secure connection, the first thing you need to do is to install OpenSSL Library and download Database So', N'ultra-Falafel', N'C:\Users\Administrator\Pictures\img_150008.jpg', N'36104', N'36104', N'selling', N'2011-12-21 15:14:17.0000000', N'6')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'14', N'2016-08-13 00:18:47.0000000', N'Creativity is intelligence having fun. The reason why a great man is great is that he resolves to be a great man. The first step is as good as half over.', N'Mexican Restaerant', N'C:\Users\Administrator\Pictures\img_072734.png', N'96070', N'96070', N'selling', N'2016-06-01 03:15:04.0000000', N'29')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'15', N'2017-04-06 01:05:15.0000000', N'To clear or reload various internal caches, flush tables, or acquire locks, control-click your connection in the Navigation pane and select Flush and choose the flush option. You must have the reload ', N'Cake', N'C:\Users\Administrator\Pictures\img_605531.jpg', N'96845', N'96845', N'selling', N'2018-02-09 09:19:13.0000000', N'25')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'16', N'2019-05-19 10:27:34.0000000', N'It is used while your ISPs do not allow direct connections, but allows establishing HTTP connections. Anyone who has ever made anything of importance was disciplined.', N'ambi-shurros', N'C:\Users\Administrator\Pictures\img_966057.jpg', N'85448', N'85448', N'selling', N'2015-10-31 08:28:49.0000000', N'7')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'17', N'2022-01-30 13:12:39.0000000', N'Navicat Monitor requires a repository to store alerts and metrics for historical analysis. Difficult circumstances serve as a textbook of life for people.', N'Cheesecake core', N'C:\Users\Administrator\Pictures\img_166107.png', N'135529', N'135529', N'selling', N'2022-08-21 17:05:42.0000000', N'5')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'18', N'2001-11-16 17:42:30.0000000', N'How we spend our days is, of course, how we spend our lives. To successfully establish a new connection to local/remote server - no matter via SSL, SSH or HTTP, set the database login information in t', N'ifamen', N'C:\Users\Administrator\Pictures\img_413665.png', N'103814', N'103814', N'selling', N'2003-10-05 10:21:54.0000000', N'5')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'19', N'2012-04-15 14:25:59.0000000', N'Genius is an infinite capacity for taking pains. Navicat 15 has added support for the system-wide dark mode. There is no way to happiness. Happiness is the way.', N'Meatloaf', N'C:\Users\Administrator\Pictures\img_060081.png', N'41595', N'41595', N'selling', N'2003-07-27 20:56:33.0000000', N'4')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'20', N'2023-01-29 15:16:23.0000000', N'Sinh tố xoài', N'Sinh tố trái cây - Xoài', N'1684291781867.jpg', N'30000', N'30000', N'selling', N'2008-02-18 06:39:14.0000000', N'14')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'21', N'2004-09-25 22:59:50.0000000', N'Creativity is intelligence having fun. Optimism is the one quality more associated with success and happiness than any other. To start working with your server in Navicat, you should first establish a', N'Waffae', N'C:\Users\Administrator\Pictures\img_038207.jpg', N'162799', N'162799', N'selling', N'2008-04-08 02:34:57.0000000', N'9')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'22', N'2016-02-03 06:23:09.0000000', N'To successfully establish a new connection to local/remote server - no matter via SSL, SSH or HTTP, set the database login information in the General tab.', N'ultra-Eggs', N'C:\Users\Administrator\Pictures\img_020585.png', N'101481', N'101481', N'selling', N'2019-07-15 16:02:25.0000000', N'11')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'23', N'2014-04-13 01:31:58.0000000', N'Difficult circumstances serve as a textbook of life for people. Navicat Monitor is a safe, simple and agentless remote server monitoring tool that is packed with powerful features to make your monitor', N'omni-Olive Garden', N'C:\Users\Administrator\Pictures\img_335196.png', N'115272', N'115272', N'selling', N'2000-06-20 05:40:37.0000000', N'24')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'24', N'2017-12-20 14:32:05.0000000', N'You can select any connections, objects or projects, and then select the corresponding buttons on the Information Pane. It is used while your ISPs do not allow direct connections, but allows establish', N'ultra-qutter', N'C:\Users\Administrator\Pictures\img_896076.png', N'144533', N'144533', N'selling', N'2008-10-24 19:21:55.0000000', N'20')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'25', N'2006-02-15 21:39:11.0000000', N'Import Wizard allows you to import data to tables/collections from CSV, TXT, XML, DBF and more. There is no way to happiness. Happiness is the way.', N'Doughnvt', N'C:\Users\Administrator\Pictures\img_783350.jpg', N'133665', N'133665', N'selling', N'2009-12-27 09:19:03.0000000', N'22')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'26', N'2000-07-10 21:46:32.0000000', N'How we spend our days is, of course, how we spend our lives. Flexible settings enable you to set up a custom key for comparison and synchronization. I may not have gone where I intended to go, but I t', N'Hutpot', N'C:\Users\Administrator\Pictures\img_641424.png', N'22104', N'22104', N'selling', N'2022-02-25 04:41:16.0000000', N'23')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'27', N'2002-08-12 09:19:18.0000000', N'You cannot save people, you can just love them. Anyone who has ever made anything of importance was disciplined. Navicat Cloud could not connect and access your databases. By which it means, it could ', N'Olive Garden', N'C:\Users\Administrator\Pictures\img_450212.jpg', N'12280', N'12280', N'selling', N'2003-04-18 23:38:13.0000000', N'12')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'28', N'2010-10-17 12:17:18.0000000', N'Monitored servers include MySQL, MariaDB and SQL Server, and compatible with cloud databases like Amazon RDS, Amazon Aurora, Oracle Cloud, Google Cloud and Microsoft Azure. Success consists of going f', N'Birthday Cake', N'C:\Users\Administrator\Pictures\img_700994.jpg', N'188944', N'188944', N'selling', N'2012-09-03 09:16:36.0000000', N'7')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'29', N'2007-03-12 00:26:19.0000000', N'It wasn’t raining when Noah built the ark. To get a secure connection, the first thing you need to do is to install OpenSSL Library and download Database Source.', N'gasta', N'C:\Users\Administrator\Pictures\img_265088.png', N'92712', N'92712', N'selling', N'2010-01-15 01:03:25.0000000', N'29')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'30', N'2018-01-30 19:53:42.0000000', N'Import Wizard allows you to import data to tables/collections from CSV, TXT, XML, DBF and more. Navicat authorizes you to make connection to remote servers running on different platforms (i.e. Windows', N'Potvto se', N'C:\Users\Administrator\Pictures\img_375245.jpg', N'171128', N'171128', N'selling', N'2005-06-01 23:14:28.0000000', N'6')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'31', N'2014-09-21 12:20:18.0000000', N'In other words, Navicat provides the ability for data in different databases and/or schemas to be kept up-to-date so that each repository contains the same information.', N'Chinese Fsod', N'C:\Users\Administrator\Pictures\img_988105.png', N'171883', N'171883', N'selling', N'2011-11-22 00:47:07.0000000', N'1')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'32', N'2004-08-01 17:34:14.0000000', N'A man’s best friends are his ten fingers. To connect to a database or schema, simply double-click it in the pane. Actually it is just in an idea when feel oneself can achieve and cannot achieve.', N'Chilis', N'C:\Users\Administrator\Pictures\img_786409.jpg', N'16078', N'16078', N'selling', N'2001-05-17 13:52:41.0000000', N'1')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'33', N'2012-11-14 18:14:12.0000000', N'Typically, it is employed as an encrypted version of Telnet. You will succeed because most people are lazy.', N'Momos mini', N'C:\Users\Administrator\Pictures\img_396764.jpg', N'48509', N'48509', N'selling', N'2009-03-17 18:00:17.0000000', N'21')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'34', N'2001-02-17 11:36:51.0000000', N'After logged in the Navicat Cloud feature, the Navigation pane will be divided into Navicat Cloud and My Connections sections. SSH serves to prevent such vulnerabilities and allows you to access a rem', N'Chinese Restaurant air', N'C:\Users\Administrator\Pictures\img_631205.jpg', N'84088', N'84088', N'selling', N'2006-03-31 19:48:16.0000000', N'2')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'35', N'2017-04-16 03:11:49.0000000', N'Kiwi ngoại nhập', N'Nước ép trái cây - Kiwi', N'1684292368895.jpg', N'70000', N'70000', N'selling', N'2013-11-18 13:52:32.0000000', N'14')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'36', N'2002-09-04 17:29:02.0000000', N'I destroy my enemies when I make them my friends. There is no way to happiness. Happiness is the way. What you get by achieving your goals is not as important as what you become by achieving your goal', N'Medican Restaurant', N'C:\Users\Administrator\Pictures\img_198741.png', N'49606', N'49606', N'selling', N'2003-08-14 14:28:41.0000000', N'29')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'37', N'2015-02-11 05:27:14.0000000', N'Creativity is intelligence having fun. SQL Editor allows you to create and edit SQL text, prepare and execute selected queries. Success consists of going from failure to failure without loss of enthus', N'Mexicun Restaurant', N'C:\Users\Administrator\Pictures\img_560144.jpg', N'130988', N'130988', N'selling', N'2003-06-11 15:00:30.0000000', N'2')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'38', N'2001-08-14 02:13:24.0000000', N'Creativity is intelligence having fun. Navicat provides powerful tools for working with queries: Query Editor for editing the query text directly, and Query Builder, Find Builder or Aggregate Builder ', N'Biryrni pi', N'C:\Users\Administrator\Pictures\img_274624.jpg', N'89344', N'89344', N'selling', N'2015-04-23 16:00:28.0000000', N'12')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'39', N'2009-04-21 15:12:58.0000000', N'Navicat Data Modeler enables you to build high-quality conceptual, logical and physical data models for a wide variety of audiences.', N'rirthday Cake', N'C:\Users\Administrator\Pictures\img_588678.png', N'199877', N'199877', N'selling', N'2011-09-01 22:54:53.0000000', N'19')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'40', N'2015-08-21 23:31:40.0000000', N'If opportunity doesn’t knock, build a door. Champions keep playing until they get it right. To start working with your server in Navicat, you should first establish a connection or several connectio', N'Cheesecake', N'C:\Users\Administrator\Pictures\img_194263.jpg', N'199107', N'199107', N'selling', N'2001-12-25 16:39:13.0000000', N'2')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'41', N'2012-10-18 12:06:15.0000000', N'Secure SHell (SSH) is a program to log in into another computer over a network, execute commands on a remote server, and move files from one machine to another. With its well-designed Graphical User I', N'Potato mini', N'C:\Users\Administrator\Pictures\img_687881.png', N'35550', N'35550', N'selling', N'2012-11-15 15:38:35.0000000', N'11')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'42', N'2015-08-31 07:58:07.0000000', N'Navicat Data Modeler is a powerful and cost-effective database design tool which helps you build high-quality conceptual, logical and physical data models.', N'Cake', N'C:\Users\Administrator\Pictures\img_231589.jpg', N'89242', N'89242', N'selling', N'2008-04-16 20:14:14.0000000', N'16')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'43', N'2021-03-06 15:09:56.0000000', N'Navicat authorizes you to make connection to remote servers running on different platforms (i.e. Windows, macOS, Linux and UNIX), and supports PAM and GSSAPI authentication.', N'aggs', N'C:\Users\Administrator\Pictures\img_537494.jpg', N'85490', N'85490', N'selling', N'2016-01-21 11:42:17.0000000', N'27')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'44', N'2018-10-23 03:57:40.0000000', N'Export Wizard allows you to export data from tables, collections, views, or query results to any available formats. You can select any connections, objects or projects, and then select the correspondi', N'Taxas', N'C:\Users\Administrator\Pictures\img_143278.jpg', N'60721', N'60721', N'selling', N'2009-11-02 16:11:42.0000000', N'17')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'45', N'2010-07-27 04:10:33.0000000', N'Khoai loại nhỏ', N'Khoai Tây Chiên', N'1684250650298.jpg', N'19000', N'19000', N'selling', N'2005-04-03 08:43:02.0000000', N'3')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'46', N'2012-02-10 07:37:40.0000000', N'It collects process metrics such as CPU load, RAM usage, and a variety of other resources over SSH/SNMP. In the middle of winter I at last discovered that there was in me an invincible summer.', N'Cakh Recipe', N'C:\Users\Administrator\Pictures\img_310817.jpg', N'139896', N'139896', N'selling', N'2015-08-28 07:41:57.0000000', N'26')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'47', N'2016-05-08 06:15:03.0000000', N'Anyone who has never made a mistake has never tried anything new. It collects process metrics such as CPU load, RAM usage, and a variety of other resources over SSH/SNMP. The Synchronize to Database f', N'Mexican Food core', N'C:\Users\Administrator\Pictures\img_502465.png', N'69194', N'69194', N'selling', N'2007-10-29 15:41:40.0000000', N'8')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'48', N'2004-05-13 18:35:04.0000000', N'All the Navicat Cloud objects are located under different projects. You can share the project to other Navicat Cloud accounts for collaboration.', N'ambi-Paella', N'C:\Users\Administrator\Pictures\img_437028.png', N'185905', N'185905', N'selling', N'2006-03-25 05:07:20.0000000', N'17')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'49', N'2005-07-19 20:57:18.0000000', N'The Information Pane shows the detailed object information, project activities, the DDL of database objects, object dependencies, membership of users/roles and preview. Navicat Cloud could not connect', N'omni-Olyve Garden', N'C:\Users\Administrator\Pictures\img_478649.png', N'190709', N'190709', N'selling', N'2020-11-09 19:34:02.0000000', N'5')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'50', N'2003-09-17 15:10:03.0000000', N'The Information Pane shows the detailed object information, project activities, the DDL of database objects, object dependencies, membership of users/roles and preview.', N'Fried Chicken', N'C:\Users\Administrator\Pictures\img_038435.png', N'126658', N'126658', N'selling', N'2017-07-19 02:27:08.0000000', N'29')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'51', N'2002-03-30 08:49:54.0000000', N'The Synchronize to Database function will give you a full picture of all database differences. You must be the change you wish to see in the world.', N'Noodles pi', N'C:\Users\Administrator\Pictures\img_186615.png', N'64361', N'64361', N'selling', N'2020-07-27 20:12:26.0000000', N'12')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'52', N'2014-10-25 00:40:51.0000000', N'The Information Pane shows the detailed object information, project activities, the DDL of database objects, object dependencies, membership of users/roles and preview. Navicat authorizes you to make ', N'Chicken', N'C:\Users\Administrator\Pictures\img_339553.jpg', N'20933', N'20933', N'selling', N'2020-10-17 13:45:01.0000000', N'26')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'53', N'2018-11-21 20:26:49.0000000', N'To successfully establish a new connection to local/remote server - no matter via SSL, SSH or HTTP, set the database login information in the General tab.', N'ambi-Korean Bbq', N'C:\Users\Administrator\Pictures\img_205974.png', N'175436', N'175436', N'selling', N'2009-12-15 09:48:32.0000000', N'27')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'54', N'2004-01-21 15:26:49.0000000', N'Ly khổng lồ', N'Trà tắc - Size L', N'1684292448269.jpg', N'25000', N'25000', N'selling', N'2007-05-25 22:09:50.0000000', N'14')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'55', N'2015-12-17 10:27:38.0000000', N'To clear or reload various internal caches, flush tables, or acquire locks, control-click your connection in the Navigation pane and select Flush and choose the flush option. You must have the reload ', N'Pastc Recipes', N'C:\Users\Administrator\Pictures\img_219482.jpg', N'81294', N'81294', N'selling', N'2020-09-19 12:27:42.0000000', N'6')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'56', N'2005-10-17 09:50:04.0000000', N'It is used while your ISPs do not allow direct connections, but allows establishing HTTP connections. The Navigation pane employs tree structure which allows you to take action upon the database and t', N'Ramen', N'C:\Users\Administrator\Pictures\img_672413.png', N'106253', N'106253', N'selling', N'2020-07-17 21:27:13.0000000', N'22')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'57', N'2019-05-29 04:14:39.0000000', N'Anyone who has ever made anything of importance was disciplined. If opportunity doesn’t knock, build a door. Navicat allows you to transfer data from one database and/or schema to another with detai', N'Chicken', N'C:\Users\Administrator\Pictures\img_849281.png', N'122268', N'122268', N'selling', N'2018-04-15 04:16:02.0000000', N'20')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'58', N'2014-02-03 15:58:30.0000000', N'If the plan doesn’t work, change the plan, but never the goal. After logged in the Navicat Cloud feature, the Navigation pane will be divided into Navicat Cloud and My Connections sections.', N'Innian Food', N'C:\Users\Administrator\Pictures\img_263436.png', N'74082', N'74082', N'selling', N'2013-11-08 09:06:03.0000000', N'11')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'59', N'2014-10-05 05:19:42.0000000', N'Secure SHell (SSH) is a program to log in into another computer over a network, execute commands on a remote server, and move files from one machine to another.', N'Chicken Recipes premium', N'C:\Users\Administrator\Pictures\img_410048.jpg', N'10851', N'10851', N'selling', N'2021-03-02 19:17:59.0000000', N'15')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'60', N'2013-09-15 08:19:30.0000000', N'Navicat is a multi-connections Database Administration tool allowing you to connect to MySQL, Oracle, PostgreSQL, SQLite, SQL Server, MariaDB and/or MongoDB databases, making database administration t', N'iBvtter', N'C:\Users\Administrator\Pictures\img_655331.jpg', N'11576', N'11576', N'selling', N'2005-05-26 11:42:08.0000000', N'2')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'61', N'2022-05-09 12:45:14.0000000', N'Difficult circumstances serve as a textbook of life for people. It is used while your ISPs do not allow direct connections, but allows establishing HTTP connections.', N'xChicken Biryani', N'C:\Users\Administrator\Pictures\img_345657.png', N'190142', N'190142', N'selling', N'2017-07-17 06:45:00.0000000', N'22')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'62', N'2012-02-22 02:28:53.0000000', N'Creativity is intelligence having fun. Typically, it is employed as an encrypted version of Telnet. Instead of wondering when your next vacation is, maybe you should set up a life you don’t need to ', N'Meatloaf mini', N'C:\Users\Administrator\Pictures\img_428500.jpg', N'51006', N'51006', N'selling', N'2008-06-07 10:03:22.0000000', N'2')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'63', N'2021-12-27 12:12:53.0000000', N'After comparing data, the window shows the number of records that will be inserted, updated or deleted in the target. If the plan doesn’t work, change the plan, but never the goal. If opportunity do', N'Tapas', N'C:\Users\Administrator\Pictures\img_530198.jpg', N'38495', N'38495', N'selling', N'2013-04-30 02:34:17.0000000', N'23')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'64', N'2016-05-12 19:15:35.0000000', N'Flexible settings enable you to set up a custom key for comparison and synchronization. All journeys have secret destinations of which the traveler is unaware. What you get by achieving your goals is ', N'xFalafel', N'C:\Users\Administrator\Pictures\img_755857.png', N'40504', N'40504', N'selling', N'2004-11-30 20:31:34.0000000', N'11')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'65', N'2004-08-23 17:20:00.0000000', N'A man is not old until regrets take the place of dreams. The Information Pane shows the detailed object information, project activities, the DDL of database objects, object dependencies, membership of', N'Birthday Cake se', N'C:\Users\Administrator\Pictures\img_767157.png', N'105223', N'105223', N'deleted', N'2003-04-04 05:09:47.0000000', N'3')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'66', N'2019-12-21 15:57:52.0000000', N'The reason why a great man is great is that he resolves to be a great man. In a Telnet session, all communications, including username and password, are transmitted in plain-text, allowing anyone to l', N'ultra-aggs', N'C:\Users\Administrator\Pictures\img_745437.png', N'177015', N'177015', N'selling', N'2016-09-08 11:13:37.0000000', N'13')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'67', N'2006-07-04 14:50:43.0000000', N'In the Objects tab, you can use the List List, Detail Detail and ER Diagram ER Diagram buttons to change the object view. To clear or reload various internal caches, flush tables, or acquire locks, co', N'Douggnut elite', N'C:\Users\Administrator\Pictures\img_046676.png', N'132628', N'132628', N'selling', N'2011-11-11 18:01:30.0000000', N'4')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'68', N'2007-02-16 03:12:09.0000000', N'Genius is an infinite capacity for taking pains. Anyone who has never made a mistake has never tried anything new. Success consists of going from failure to failure without loss of enthusiasm.', N'Chicken Biryani se', N'C:\Users\Administrator\Pictures\img_227425.png', N'29533', N'29533', N'selling', N'2004-04-27 19:11:23.0000000', N'16')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'69', N'2012-02-05 21:32:21.0000000', N'The past has no power over the present moment. Navicat Monitor requires a repository to store alerts and metrics for historical analysis. If the Show objects under schema in navigation pane option is ', N'Biryani', N'C:\Users\Administrator\Pictures\img_378933.jpg', N'194648', N'194648', N'selling', N'2021-08-07 07:34:28.0000000', N'5')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'70', N'2009-11-14 16:31:57.0000000', N'I destroy my enemies when I make them my friends. The first step is as good as half over. The first step is as good as half over. Optimism is the one quality more associated with success and happiness', N'iPancakes', N'C:\Users\Administrator\Pictures\img_740098.jpg', N'99999', N'99999', N'selling', N'2017-12-20 06:18:37.0000000', N'17')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'71', N'2010-10-15 21:28:56.0000000', N'Monitored servers include MySQL, MariaDB and SQL Server, and compatible with cloud databases like Amazon RDS, Amazon Aurora, Oracle Cloud, Google Cloud and Microsoft Azure.', N'gheesecake mini', N'C:\Users\Administrator\Pictures\img_690402.jpg', N'163830', N'163830', N'selling', N'2020-09-09 03:54:12.0000000', N'25')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'72', N'2014-03-05 11:48:13.0000000', N'The Information Pane shows the detailed object information, project activities, the DDL of database objects, object dependencies, membership of users/roles and preview.', N'Chuesecake', N'C:\Users\Administrator\Pictures\img_975860.jpg', N'150233', N'150233', N'selling', N'2019-08-21 05:59:37.0000000', N'22')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'73', N'2014-04-19 22:14:30.0000000', N'Navicat Monitor is a safe, simple and agentless remote server monitoring tool that is packed with powerful features to make your monitoring effective as possible. It wasn’t raining when Noah built t', N'ambi-Falafel', N'C:\Users\Administrator\Pictures\img_566490.png', N'58399', N'58399', N'selling', N'2005-12-06 03:49:14.0000000', N'7')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'74', N'2008-04-10 05:14:16.0000000', N'A query is used to extract data from the database in a readable format according to the user''s request. The Navigation pane employs tree structure which allows you to take action upon the database and', N'Doughnut', N'C:\Users\Administrator\Pictures\img_118485.jpg', N'75560', N'75560', N'selling', N'2005-05-24 01:19:47.0000000', N'19')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'75', N'2016-05-09 12:36:41.0000000', N'What you get by achieving your goals is not as important as what you become by achieving your goals. HTTP Tunneling is a method for connecting to a server that uses the same protocol (http://) and the', N'iCake Recipe', N'C:\Users\Administrator\Pictures\img_140312.png', N'136050', N'136050', N'selling', N'2018-02-23 13:10:46.0000000', N'13')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'76', N'2014-05-08 07:02:00.0000000', N'What you get by achieving your goals is not as important as what you become by achieving your goals. Difficult circumstances serve as a textbook of life for people.', N'ambi-Chilis', N'C:\Users\Administrator\Pictures\img_992496.jpg', N'53832', N'53832', N'deleted', N'2004-03-23 11:18:09.0000000', N'14')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'77', N'2014-04-19 09:24:28.0000000', N'The Synchronize to Database function will give you a full picture of all database differences. Anyone who has ever made anything of importance was disciplined.', N'Koreax Bbq air', N'C:\Users\Administrator\Pictures\img_718999.jpg', N'191363', N'191363', N'selling', N'2011-08-04 14:40:36.0000000', N'15')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'78', N'2015-02-08 18:52:04.0000000', N'The Navigation pane employs tree structure which allows you to take action upon the database and their objects through their pop-up menus quickly and easily.', N'Tapas pi', N'C:\Users\Administrator\Pictures\img_116910.jpg', N'103564', N'103564', N'selling', N'2006-12-10 11:02:22.0000000', N'1')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'79', N'2008-11-14 22:28:48.0000000', N'Navicat authorizes you to make connection to remote servers running on different platforms (i.e. Windows, macOS, Linux and UNIX), and supports PAM and GSSAPI authentication. Flexible settings enable y', N'Chicken Recipes core', N'C:\Users\Administrator\Pictures\img_262511.jpg', N'51981', N'51981', N'selling', N'2013-11-09 15:52:50.0000000', N'12')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'80', N'2003-05-22 12:15:03.0000000', N'Flexible settings enable you to set up a custom key for comparison and synchronization. I may not have gone where I intended to go, but I think I have ended up where I needed to be.', N'Ramen core', N'C:\Users\Administrator\Pictures\img_179372.jpg', N'172565', N'172565', N'selling', N'2003-06-10 06:31:27.0000000', N'20')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'81', N'2006-09-15 12:01:30.0000000', N'A comfort zone is a beautiful place, but nothing ever grows there. Instead of wondering when your next vacation is, maybe you should set up a life you don’t need to escape from.', N'oake pro', N'C:\Users\Administrator\Pictures\img_120033.jpg', N'115096', N'115096', N'selling', N'2002-12-05 06:58:03.0000000', N'5')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'82', N'2009-06-05 11:58:33.0000000', N'Gồm: sườn, bì, trứng chiên', N'Cơm sườn 2', N'1684295929846.jpg', N'40000', N'40000', N'selling', N'2004-11-18 20:07:23.0000000', N'18')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'83', N'2021-06-23 15:45:15.0000000', N'If your Internet Service Provider (ISP) does not provide direct access to its server, Secure Tunneling Protocol (SSH) / HTTP is another solution.', N'Shawarma pi', N'C:\Users\Administrator\Pictures\img_810833.jpg', N'134353', N'134353', N'selling', N'2005-04-17 21:58:33.0000000', N'12')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'84', N'2008-08-26 02:34:51.0000000', N'I will greet this day with love in my heart. Champions keep playing until they get it right. You can select any connections, objects or projects, and then select the corresponding buttons on the Infor', N'Mexican Food', N'C:\Users\Administrator\Pictures\img_006725.jpg', N'107037', N'107037', N'selling', N'2001-05-12 08:09:44.0000000', N'26')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'85', N'2010-03-28 04:51:24.0000000', N'It wasn’t raining when Noah built the ark. I will greet this day with love in my heart. I may not have gone where I intended to go, but I think I have ended up where I needed to be.', N'Indian Food', N'C:\Users\Administrator\Pictures\img_432089.png', N'37629', N'37629', N'selling', N'2012-06-05 08:04:38.0000000', N'28')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'86', N'2013-08-18 00:30:34.0000000', N'Navicat Cloud provides a cloud service for synchronizing connections, queries, model files and virtual group information from Navicat, other Navicat family members, different machines and different pl', N'Eggs se', N'C:\Users\Administrator\Pictures\img_895884.jpg', N'42248', N'42248', N'selling', N'2012-08-23 13:03:43.0000000', N'20')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'87', N'2011-02-02 08:20:35.0000000', N'Navicat Monitor can be installed on any local computer or virtual machine and does not require any software installation on the servers being monitored.', N'Churros', N'C:\Users\Administrator\Pictures\img_518571.png', N'154610', N'154610', N'selling', N'2020-08-03 03:57:33.0000000', N'11')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'88', N'2009-03-25 00:48:33.0000000', N'The first step is as good as half over. In the Objects tab, you can use the List List, Detail Detail and ER Diagram ER Diagram buttons to change the object view. The Main Window consists of several to', N'omni-Noodles', N'C:\Users\Administrator\Pictures\img_668006.png', N'37565', N'37565', N'selling', N'2000-10-23 14:00:13.0000000', N'10')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'89', N'2010-07-05 21:36:33.0000000', N'Difficult circumstances serve as a textbook of life for people. To connect to a database or schema, simply double-click it in the pane.', N'Tapas core', N'C:\Users\Administrator\Pictures\img_810489.png', N'175228', N'175228', N'selling', N'2001-05-18 12:59:14.0000000', N'19')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'90', N'2021-02-06 05:27:44.0000000', N'Secure Sockets Layer(SSL) is a protocol for transmitting private documents via the Internet. You must be the change you wish to see in the world. Navicat 15 has added support for the system-wide dark ', N'Shawarma', N'C:\Users\Administrator\Pictures\img_806207.jpg', N'76989', N'76989', N'selling', N'2023-01-26 19:16:02.0000000', N'24')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'91', N'2010-02-01 09:32:50.0000000', N'Gồm: sườn, bì, trứng ốp la, lạp xưởng', N'Cơm sườn 3', N'1684296107670.jpg', N'50000', N'50000', N'selling', N'2010-03-26 12:30:15.0000000', N'18')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'92', N'2017-07-06 03:45:21.0000000', N'A query is used to extract data from the database in a readable format according to the user''s request. To connect to a database or schema, simply double-click it in the pane.', N'Chicken Recipes', N'C:\Users\Administrator\Pictures\img_283273.jpg', N'112889', N'112889', N'selling', N'2016-05-23 19:16:25.0000000', N'4')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'93', N'2016-06-29 10:49:08.0000000', N'You cannot save people, you can just love them. I may not have gone where I intended to go, but I think I have ended up where I needed to be.', N'Cake', N'C:\Users\Administrator\Pictures\img_889434.jpg', N'37270', N'37270', N'selling', N'2004-11-24 04:10:20.0000000', N'8')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'94', N'2017-12-15 06:25:45.0000000', N'What you get by achieving your goals is not as important as what you become by achieving your goals. Navicat Cloud could not connect and access your databases. By which it means, it could only store y', N'Tapas', N'C:\Users\Administrator\Pictures\img_225228.jpg', N'152295', N'152295', N'selling', N'2013-05-28 09:51:56.0000000', N'16')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'95', N'2017-09-19 02:53:54.0000000', N'If the Show objects under schema in navigation pane option is checked at the Preferences window, all database objects are also displayed in the pane.', N'Cake Recire', N'C:\Users\Administrator\Pictures\img_102708.jpg', N'177597', N'177597', N'selling', N'2005-08-27 11:01:14.0000000', N'9')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'96', N'2002-04-20 12:46:02.0000000', N'After comparing data, the window shows the number of records that will be inserted, updated or deleted in the target. If you wait, all that happens is you get older. Import Wizard allows you to import', N'xBiryani', N'C:\Users\Administrator\Pictures\img_758302.png', N'137783', N'137783', N'selling', N'2009-10-12 00:40:49.0000000', N'13')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'97', N'2019-11-11 15:10:29.0000000', N'Navicat 15 has added support for the system-wide dark mode. Navicat Monitor is a safe, simple and agentless remote server monitoring tool that is packed with powerful features to make your monitoring ', N'fotpot', N'C:\Users\Administrator\Pictures\img_862465.png', N'98903', N'98903', N'selling', N'2001-10-04 17:41:56.0000000', N'26')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'98', N'2019-09-02 21:03:24.0000000', N'Champions keep playing until they get it right. Import Wizard allows you to import data to tables/collections from CSV, TXT, XML, DBF and more. The repository database can be an existing MySQL, MariaD', N'Dessert air', N'C:\Users\Administrator\Pictures\img_395783.png', N'140924', N'140924', N'selling', N'2021-01-12 00:25:39.0000000', N'26')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'99', N'2012-06-21 00:09:38.0000000', N'A query is used to extract data from the database in a readable format according to the user''s request. To get a secure connection, the first thing you need to do is to install OpenSSL Library and dow', N'Chxcken', N'C:\Users\Administrator\Pictures\img_926102.png', N'88235', N'88235', N'deleted', N'2022-10-13 14:48:28.0000000', N'3')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'100', N'2021-08-25 17:41:11.0000000', N'The Information Pane shows the detailed object information, project activities, the DDL of database objects, object dependencies, membership of users/roles and preview. Navicat Monitor is a safe, simp', N'Korean Bbq core', N'C:\Users\Administrator\Pictures\img_467690.jpg', N'67334', N'67334', N'selling', N'2009-01-14 11:02:43.0000000', N'28')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'102', N'2023-05-10 16:50:21.8160000', N'Cơm đùi gà nướng + cơm trắng', N'Cơm gà nướng', N'1684297034834.png', N'40000', N'40000', N'selling', N'2023-05-10 16:50:21.8160000', N'18')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'103', N'2023-05-11 09:41:17.2580000', N'Đùi gà + cơm chiên', N'Cơm gà xối mỡ', N'1684312800073.jpg', N'40000', N'40000', N'selling', N'2023-05-11 09:41:17.2580000', N'18')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'104', N'2023-05-11 09:42:53.6270000', N'Bún + 2 xiên thịt nướng', N'Bún thịt nướng xiên que', N'1684312976076.jpg', N'25000', N'25000', N'selling', N'2023-05-11 09:42:53.6270000', N'18')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'108', N'2023-05-11 15:00:00.5120000', N'', N'Cơm tấm sườn bì', N'1683792000512.webp', N'30000', N'30000', N'sold_out', N'2023-05-11 15:00:00.5120000', N'18')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'109', N'2023-05-11 15:01:48.0430000', N'Bún thịt nướng có chả giò', N'Bún thịt nướng', N'1683792108043.webp', N'30000', N'30000', N'sold_out', N'2023-05-11 15:01:48.0430000', N'18')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'110', N'2023-05-11 15:16:04.8400000', N'8k/miếng', N'Trứng hấp', N'1684296205206.jpg', N'8000', N'8000', N'selling', N'2023-05-11 15:16:04.8400000', N'18')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'111', N'2023-05-11 15:17:48.3530000', N'4k/cái', N'Chả giò', N'1683793068353.webp', N'4000', N'4000', N'sold_out', N'2023-05-11 15:17:48.3530000', N'18')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'112', N'2023-05-11 16:20:31.7560000', N'', N'Hủ tiếu', N'1683796831760.webp', N'50000', N'50000', N'deleted', N'2023-05-11 16:20:31.7560000', N'18')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'113', N'2023-05-16 22:28:51.0940000', N'Gồm: 1 gà popcorn, 1 khoai tây nhỏ, 1 nước pepsi', N'Combo 1', N'1684250931095.jpg', N'99000', N'99000', N'selling', N'2023-05-16 22:28:51.0940000', N'3')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'114', N'2023-05-16 22:46:26.7370000', N'Gồm: 2 đùi gà rán, 1 khoai tây nhỏ, 1 chai pepsi', N'Combo 2', N'1684251986743.jpg', N'150000', N'150000', N'sold_out', N'2023-05-16 22:46:26.7370000', N'3')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'115', N'2023-05-16 22:46:26.9530000', N'Gồm: 2 đùi gà rán, 1 khoai tây nhỏ, 1 chai pepsi', N'Combo 2', N'1684251986953.jpg', N'150000', N'150000', N'deleted', N'2023-05-16 22:46:26.9530000', N'3')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'116', N'2023-05-16 22:48:52.2190000', N'Gồm: 1 hamburger, 1 đùi gà rán, 1 pepsi', N'Combo 3', N'1684252132219.jpg', N'150000', N'150000', N'selling', N'2023-05-16 22:48:52.2190000', N'3')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'117', N'2023-05-17 10:01:42.4520000', N'Ly vừa', N'Trà tắc - Size M', N'1684292502452.jpg', N'20000', N'20000', N'selling', N'2023-05-17 10:01:42.4520000', N'14')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'118', N'2023-05-17 11:05:59.0900000', N'', N'Cơm thêm', N'1684296359092.jpg', N'10000', N'10000', N'deleted', N'2023-05-17 11:05:59.0900000', N'18')
GO

INSERT INTO [dbo].[food] ([food_id], [created_at], [description], [food_name], [image], [price], [price_promotion], [status], [updated_at], [store_store_id]) VALUES (N'119', N'2023-05-18 10:13:22.8550000', N'Ly to', N'Cơm sườn 1', N'1684379602855.jpg', N'20000', N'20000', N'selling', N'2023-05-18 10:13:22.8550000', N'18')
GO

SET IDENTITY_INSERT [dbo].[food] OFF
GO


-- ----------------------------
-- Table structure for order_item
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[order_item]') AND type IN ('U'))
	DROP TABLE [dbo].[order_item]
GO

CREATE TABLE [dbo].[order_item] (
  [order_item_id] bigint  IDENTITY(1,1) NOT NULL,
  [amount] int  NOT NULL,
  [food_price] float(53)  NULL,
  [food_food_id] bigint  NULL,
  [order_order_id] bigint  NULL
)
GO

ALTER TABLE [dbo].[order_item] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of order_item
-- ----------------------------
SET IDENTITY_INSERT [dbo].[order_item] ON
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'72', N'1', N'105000', N'9', N'43')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'73', N'2', N'105000', N'9', N'44')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'74', N'1', N'30000', N'108', N'45')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'75', N'1', N'150000', N'103', N'45')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'76', N'1', N'30000', N'108', N'46')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'77', N'1', N'150000', N'103', N'46')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'78', N'1', N'110000', N'102', N'46')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'79', N'2', N'105000', N'9', N'47')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'80', N'3', N'45000', N'11', N'47')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'81', N'1', N'105000', N'9', N'48')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'82', N'1', N'45000', N'11', N'49')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'83', N'1', N'19000', N'45', N'49')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'84', N'1', N'150000', N'116', N'50')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'85', N'1', N'150000', N'114', N'50')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'86', N'1', N'99000', N'113', N'50')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'87', N'1', N'150000', N'2', N'51')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'88', N'2', N'45000', N'82', N'51')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'89', N'3', N'35000', N'12', N'52')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'90', N'3', N'35000', N'12', N'53')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'91', N'1', N'45000', N'82', N'54')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'92', N'1', N'45000', N'11', N'55')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'93', N'3', N'19000', N'45', N'55')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'94', N'1', N'105000', N'9', N'55')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'95', N'1', N'150000', N'114', N'55')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'96', N'3', N'105000', N'9', N'56')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'97', N'1', N'45000', N'11', N'56')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'98', N'2', N'19000', N'45', N'56')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'99', N'2', N'40000', N'2', N'57')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'100', N'1', N'40000', N'82', N'57')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'101', N'1', N'40000', N'2', N'58')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'102', N'1', N'40000', N'102', N'58')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'103', N'2', N'150000', N'103', N'58')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'104', N'1', N'8000', N'110', N'58')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'105', N'1', N'10000', N'118', N'58')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'106', N'1', N'40000', N'2', N'59')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'107', N'1', N'105000', N'9', N'60')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'108', N'1', N'30000', N'108', N'61')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'109', N'1', N'10000', N'118', N'61')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'110', N'1', N'8000', N'110', N'61')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'111', N'1', N'40000', N'2', N'62')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'112', N'1', N'50000', N'91', N'62')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'113', N'1', N'10000', N'118', N'62')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'114', N'1', N'105000', N'9', N'63')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'115', N'1', N'150000', N'116', N'63')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'116', N'1', N'105000', N'9', N'64')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'117', N'1', N'150000', N'116', N'65')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'118', N'1', N'150000', N'116', N'66')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'119', N'2', N'40000', N'2', N'67')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'120', N'1', N'150000', N'116', N'68')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'121', N'1', N'150000', N'116', N'69')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'122', N'1', N'150000', N'116', N'70')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'123', N'1', N'40000', N'2', N'71')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'124', N'3', N'40000', N'2', N'72')
GO

INSERT INTO [dbo].[order_item] ([order_item_id], [amount], [food_price], [food_food_id], [order_order_id]) VALUES (N'125', N'5', N'40000', N'102', N'72')
GO

SET IDENTITY_INSERT [dbo].[order_item] OFF
GO


-- ----------------------------
-- Table structure for review
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[review]') AND type IN ('U'))
	DROP TABLE [dbo].[review]
GO

CREATE TABLE [dbo].[review] (
  [review_id] bigint  IDENTITY(1,1) NOT NULL,
  [rate] int  NOT NULL,
  [review_content] nvarchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [order_id] bigint  NULL,
  [user_profile_user_id] bigint  NULL
)
GO

ALTER TABLE [dbo].[review] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of review
-- ----------------------------
SET IDENTITY_INSERT [dbo].[review] ON
GO

INSERT INTO [dbo].[review] ([review_id], [rate], [review_content], [order_id], [user_profile_user_id]) VALUES (N'42', N'5', N'Món ăn rất ngon', N'47', N'2')
GO

INSERT INTO [dbo].[review] ([review_id], [rate], [review_content], [order_id], [user_profile_user_id]) VALUES (N'43', N'5', N'Quán ăn ngon lắm. Sẽ tiếp tục ủng hộ 🥰🥰🥰', N'58', N'8')
GO

INSERT INTO [dbo].[review] ([review_id], [rate], [review_content], [order_id], [user_profile_user_id]) VALUES (N'44', N'5', N'Ngon tuyệt cú mèo 🤭🤭🤭', N'45', N'7')
GO

INSERT INTO [dbo].[review] ([review_id], [rate], [review_content], [order_id], [user_profile_user_id]) VALUES (N'45', N'4', N'Món ăn ngon.Giao hàng rất nhanh', N'60', N'8')
GO

INSERT INTO [dbo].[review] ([review_id], [rate], [review_content], [order_id], [user_profile_user_id]) VALUES (N'46', N'4', N'Món ăn ngon. Giao hàng nhanh', N'61', N'8')
GO

INSERT INTO [dbo].[review] ([review_id], [rate], [review_content], [order_id], [user_profile_user_id]) VALUES (N'47', N'4', N'Ngon nha 10đ cho quán nhưng hơi lâu ', N'46', N'7')
GO

INSERT INTO [dbo].[review] ([review_id], [rate], [review_content], [order_id], [user_profile_user_id]) VALUES (N'48', N'2', N'cũm ngon', N'57', N'6')
GO

SET IDENTITY_INSERT [dbo].[review] OFF
GO


-- ----------------------------
-- Table structure for store
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[store]') AND type IN ('U'))
	DROP TABLE [dbo].[store]
GO

CREATE TABLE [dbo].[store] (
  [store_id] bigint  IDENTITY(1,1) NOT NULL,
  [address] nvarchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [avatar] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [balance] float(53)  NULL,
  [created_at] datetime2(7)  NULL,
  [fcm_token] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [phone] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [rate] float(53)  NOT NULL,
  [review_count] bigint  NULL,
  [status] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [store_name] nvarchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [updated_at] datetime2(7)  NULL
)
GO

ALTER TABLE [dbo].[store] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of store
-- ----------------------------
SET IDENTITY_INSERT [dbo].[store] ON
GO

INSERT INTO [dbo].[store] ([store_id], [address], [avatar], [balance], [created_at], [fcm_token], [phone], [rate], [review_count], [status], [store_name], [updated_at]) VALUES (N'1', N'7 Đường 385, Phường Tăng Nhơn Phú A, Quận 9, TP Thủ Đức', N'https://static.vinwonders.com/production/bun-dau-mam-tom-ha-noi-1.jpg', N'69721491.5', N'2013-03-07 02:29:40.0000000', N'c_bmi9s9SH6-Sgxwn99AdV:APA91bHbat909cIJeEnFtCmpCkeOlcL3l7dAroieDJC8pIFLi6bhu9v6EF-JDByyH6zjY21ukzSQ_hoGPj1YG8xXpeAorEP4JdVc_8HUOFt19k-7XvSo1IsMVhrDn2bjJUbJGgjjuStN', N'0995445354', N'2.73043465614319', N'23', N'closed', N'Bún Đậu Bumstop', N'2001-01-06 18:21:21.0000000')
GO

INSERT INTO [dbo].[store] ([store_id], [address], [avatar], [balance], [created_at], [fcm_token], [phone], [rate], [review_count], [status], [store_name], [updated_at]) VALUES (N'2', N'202 Man Thiện, Phường Hiệp Phú, Quận 9, TP Thủ Đức', N'https://danviet.mediacdn.vn/296231569849192448/2022/12/20/159771609-2896931810588995-3618458814222607521-n-167149640601359937673.jpg', N'75051661', N'2015-08-11 05:20:39.0000000', N'', N'0962944905', N'1.7', N'0', N'closed', N'Cơm Chay Thôi Kệ', N'2014-06-04 02:37:28.0000000')
GO

INSERT INTO [dbo].[store] ([store_id], [address], [avatar], [balance], [created_at], [fcm_token], [phone], [rate], [review_count], [status], [store_name], [updated_at]) VALUES (N'3', N'193 Lê Văn Việt, Phường Hiệp Phú, Quận 9, TP Thủ Đức', N'https://upload.wikimedia.org/wikipedia/vi/thumb/7/7e/Logo_KFC.svg/1200px-Logo_KFC.svg.png', N'100296000', N'2000-07-06 05:50:39.0000000', N'eIcBKlpIQqiMW_-Qf9Ax5j:APA91bG3nLJ0B39JokHbi0HgoLs0jIgygSy5Hl_MOH3n1b127JsPuRFpf3zEoMjlByt7Jy4lQiXNoKakqNRCdirPrhOjpszTU-8hOtBRzqzuSzhklmOVJXRASsZf1GtaYT3pp_mD3pKM', N'0913119407', N'4.5', N'2', N'closed', N'KFC - Lê Văn Việt', N'2003-06-03 16:12:56.0000000')
GO

INSERT INTO [dbo].[store] ([store_id], [address], [avatar], [balance], [created_at], [fcm_token], [phone], [rate], [review_count], [status], [store_name], [updated_at]) VALUES (N'4', N'22 Đoàn Kết, Phường Bình Thọ, Quận Thủ Đức, TP Thủ Đức', N'https://d1sag4ddilekf6.cloudfront.net/compressed_webp/items/VNITE20191004061656017337/detail/c4260b3d033f4f9da0f601c4706a6eec_1572515519642141904.webp', N'98850393', N'2016-07-08 07:22:37.0000000', NULL, N'0944387701', N'2.6', N'0', N'opening', N'Cơm Gà Shilin - Đoàn Kết', N'2015-08-23 04:13:53.0000000')
GO

INSERT INTO [dbo].[store] ([store_id], [address], [avatar], [balance], [created_at], [fcm_token], [phone], [rate], [review_count], [status], [store_name], [updated_at]) VALUES (N'5', N'31 Lê Văn Việt, Phường Hiệp Phú, Quận 9, TP Thủ Đức', N'https://media.foody.vn/res/g107/1062396/prof/s/foody-upload-api-foody-mobile-2cd9fe141c9f7b233f63-201231143813.jpg', N'62747828', N'2012-12-19 04:38:01.0000000', NULL, N'0949917065', N'2.8', N'0', N'opening', N'Cơm Gà Đệ Nhất - Lê Văn Việt', N'2011-02-08 08:14:22.0000000')
GO

INSERT INTO [dbo].[store] ([store_id], [address], [avatar], [balance], [created_at], [fcm_token], [phone], [rate], [review_count], [status], [store_name], [updated_at]) VALUES (N'6', N'33 Lê Văn Việt, Phường Hiệp Phú, Quận 9, TP Thủ Đức', N'https://tea-3.lozi.vn/v1/images/resized/com-tam-phuc-loc-tho-an-duong-vuong-quan-5-ho-chi-minh-1603688528877685390-eatery-avatar-1625928505?w=640&type=s', N'82330352', N'2019-01-22 10:36:42.0000000', NULL, N'0956558018', N'4.2', N'0', N'opening', N'Cơm Tấm Phúc Lộc Thọ - Lê Văn Việt', N'2023-01-28 08:27:08.0000000')
GO

INSERT INTO [dbo].[store] ([store_id], [address], [avatar], [balance], [created_at], [fcm_token], [phone], [rate], [review_count], [status], [store_name], [updated_at]) VALUES (N'7', N'72 Làng Tăng Phú, Phường Tăng Nhơn Phú A, Quận 9, TP Thủ Đức', N'https://d1sag4ddilekf6.cloudfront.net/compressed_webp/items/VNITE20211119103939236594/detail/menueditor_item_46b9d80dd8c54c9f90269e636f9af259_1669622185576740598.webp', N'85198144', N'2023-01-15 19:59:18.0000000', NULL, N'0972575235', N'1.8', N'0', N'opening', N'Bún Đậu Akiso - Làng Tăng Phú', N'2018-07-25 09:10:46.0000000')
GO

INSERT INTO [dbo].[store] ([store_id], [address], [avatar], [balance], [created_at], [fcm_token], [phone], [rate], [review_count], [status], [store_name], [updated_at]) VALUES (N'8', N'32 Đường 449, Phường Tăng Nhơn Phú A, Quận 9, TP Thủ Đức', N'https://posapp.vn/wp-content/uploads/2023/01/tra-sua-nong-01-1024x683.jpg', N'22490009', N'2020-03-10 06:08:12.0000000', NULL, N'0990544904', N'1.6', N'0', N'opening', N'Trà Sữa Nọng - Đường 449', N'2014-03-24 09:20:32.0000000')
GO

INSERT INTO [dbo].[store] ([store_id], [address], [avatar], [balance], [created_at], [fcm_token], [phone], [rate], [review_count], [status], [store_name], [updated_at]) VALUES (N'9', N'1012 Kha Vạn Cân, Phường Linh Chiểu, TP Thủ Đức', N'https://mfvietnam.com/wp-content/uploads/2023/04/639d4fb26949fb0d309d5aba_logo-phuc-long-coffee-and-tea.jpg', N'23961418', N'2002-07-24 22:13:20.0000000', NULL, N'0900842685', N'3.4', N'0', N'opening', N'Trà & Cà Phê Phúc Long', N'2010-03-21 22:21:04.0000000')
GO

INSERT INTO [dbo].[store] ([store_id], [address], [avatar], [balance], [created_at], [fcm_token], [phone], [rate], [review_count], [status], [store_name], [updated_at]) VALUES (N'10', N'96 Tân Lập, Dĩ An, Phường Đông Hoà, Bình Dương', N'https://1office.vn/wp-content/uploads/2020/02/36852230_419716301836700_6088975431891943424_n-1.png', N'27026494', N'2008-07-18 01:41:56.0000000', NULL, N'0941526855', N'2.7', N'0', N'opening', N'Tocotoco -Tân Lập', N'2005-12-31 03:33:51.0000000')
GO

INSERT INTO [dbo].[store] ([store_id], [address], [avatar], [balance], [created_at], [fcm_token], [phone], [rate], [review_count], [status], [store_name], [updated_at]) VALUES (N'11', N'191 Quang Trung, Phường Linh Trung, TP Thủ Đức', N'https://businessyield.com/wp-content/uploads/2022/10/pizza-hut.jpg', N'17743398', N'2005-07-06 08:58:17.0000000', NULL, N'0955936757', N'1.1', N'0', N'opening', N'Pizza Hut- Xa Lộ Hà Nội', N'2013-06-17 04:27:59.0000000')
GO

INSERT INTO [dbo].[store] ([store_id], [address], [avatar], [balance], [created_at], [fcm_token], [phone], [rate], [review_count], [status], [store_name], [updated_at]) VALUES (N'12', N'26 Võ Văn Ngân, Phường Trường Thọ, Quận Thủ Đức, TP Thủ Đức', N'https://www.gigamall.com.vn/data/2019/09/20/14514819_LOGO-THE-PIZZA-COMPANY-500x500.jpg', N'8176564', N'2023-02-06 15:46:51.0000000', NULL, N'0900259317', N'2.3', N'0', N'opening', N'The Company Pizza', N'2013-09-05 11:01:33.0000000')
GO

INSERT INTO [dbo].[store] ([store_id], [address], [avatar], [balance], [created_at], [fcm_token], [phone], [rate], [review_count], [status], [store_name], [updated_at]) VALUES (N'13', N'196 Lê Văn Việt, Phường Tăng Nhơn Phú B, Quận 9, TP Thủ Đức', N'https://play-lh.googleusercontent.com/XFoF95cacSb9XWggu2TasmSIfW-OODjkFgWUV_ib6ePh0F-RuK4m2i_2yEkCikmUOMbH', N'9246094', N'2010-05-17 06:24:58.0000000', NULL, N'0902606192', N'2.3', N'0', N'opening', N'Domino''s Pizza - Lê Văn Việt', N'2021-11-13 23:22:06.0000000')
GO

INSERT INTO [dbo].[store] ([store_id], [address], [avatar], [balance], [created_at], [fcm_token], [phone], [rate], [review_count], [status], [store_name], [updated_at]) VALUES (N'14', N'23 Lã Xuân Oai, Long Trường, TP Thủ Đức', N'https://www.huongnghiepaau.com/wp-content/uploads/2022/07/mon-sinh-to-dao.jpg', N'12658607', N'2022-05-09 10:59:00.0000000', N'c_bmi9s9SH6-Sgxwn99AdV:APA91bHbat909cIJeEnFtCmpCkeOlcL3l7dAroieDJC8pIFLi6bhu9v6EF-JDByyH6zjY21ukzSQ_hoGPj1YG8xXpeAorEP4JdVc_8HUOFt19k-7XvSo1IsMVhrDn2bjJUbJGgjjuStN', N'0976225288', N'3.5', N'0', N'opening', N'Juice House - Trái Cây, Sinh Tố & Nước Ép', N'2012-08-19 18:34:08.0000000')
GO

INSERT INTO [dbo].[store] ([store_id], [address], [avatar], [balance], [created_at], [fcm_token], [phone], [rate], [review_count], [status], [store_name], [updated_at]) VALUES (N'15', N'07 Tam Hà, Phường Tam Phú, TP Thủ Đức', N'https://horecavn.com/uploads/images/bai-viet/sinh-to-nuoc-ep-3.gif', N'54695129', N'2017-01-29 18:15:04.0000000', NULL, N'0959083541', N'1', N'0', N'opening', N'Mr.Chanh Sinh Tố Trái Cây', N'2015-12-20 11:58:18.0000000')
GO

INSERT INTO [dbo].[store] ([store_id], [address], [avatar], [balance], [created_at], [fcm_token], [phone], [rate], [review_count], [status], [store_name], [updated_at]) VALUES (N'16', N'390 Nguyễn Văn Tăng, Phường Long Thạnh Mỹ, TP Thủ Đức', N'https://cdn.tgdd.vn/Files/2022/01/25/1412805/cach-nau-pho-bo-nam-dinh-chuan-vi-thom-ngon-nhu-hang-quan-202201250230038502.jpg', N'46607085', N'2019-11-23 16:05:14.0000000', NULL, N'0993263485', N'1.1', N'0', N'opening', N'Phở Quê Hương', N'2017-10-31 03:00:03.0000000')
GO

INSERT INTO [dbo].[store] ([store_id], [address], [avatar], [balance], [created_at], [fcm_token], [phone], [rate], [review_count], [status], [store_name], [updated_at]) VALUES (N'17', N'87 Song Hành, Phường An Phú, Quận 2', N'https://bizweb.dktcdn.net/100/442/328/files/nhung-dieu-ve-mon-pho-bo-khong-phai-ai-cung-biet-3.jpg?v=1638935205292', N'85939480', N'2008-08-21 18:06:59.0000000', NULL, N'0996460604', N'4.4', N'0', N'opening', N'Phở Xưa', N'2017-09-13 00:14:42.0000000')
GO

INSERT INTO [dbo].[store] ([store_id], [address], [avatar], [balance], [created_at], [fcm_token], [phone], [rate], [review_count], [status], [store_name], [updated_at]) VALUES (N'18', N'2 Nguyễn Khuyến, Phường Tăng Nhơn Phú A, Quận 9, TP Thủ Đức', N'https://luhanhvietnam.com.vn/du-lich/vnt_upload/news/09_2022/quan-com-tam-o-ha-noi-.jpg', N'85091085.0003576', N'2007-02-19 23:58:51.0000000', N'c_bmi9s9SH6-Sgxwn99AdV:APA91bHbat909cIJeEnFtCmpCkeOlcL3l7dAroieDJC8pIFLi6bhu9v6EF-JDByyH6zjY21ukzSQ_hoGPj1YG8xXpeAorEP4JdVc_8HUOFt19k-7XvSo1IsMVhrDn2bjJUbJGgjjuStN', N'0939448194', N'4.15000009536743', N'5', N'opening', N'Cơm Tấm Lái Thiêu', N'2005-10-27 15:21:08.0000000')
GO

INSERT INTO [dbo].[store] ([store_id], [address], [avatar], [balance], [created_at], [fcm_token], [phone], [rate], [review_count], [status], [store_name], [updated_at]) VALUES (N'19', N'978 Kha Vạn Cân, Phường Linh Chiểu, TP Thủ Đức', N'https://monngonmoingay.com/wp-content/uploads/2015/11/PhoBo-e1446825512455.jpg', N'22161356', N'2006-04-23 01:47:25.0000000', NULL, N'0992924181', N'2.9', N'0', N'opening', N'Phở Hồ Tây', N'2011-04-15 07:16:45.0000000')
GO

INSERT INTO [dbo].[store] ([store_id], [address], [avatar], [balance], [created_at], [fcm_token], [phone], [rate], [review_count], [status], [store_name], [updated_at]) VALUES (N'20', N'250 Võ Văn Tần, Phường 5, Quận 3', N'https://kiddy.edu.vn/wp-content/uploads/2023/05/2-cach-lam-banh-mi-thit-heo-quay-thom-ngon.jpg', N'96774327', N'2021-12-10 17:52:26.0000000', NULL, N'0942181003', N'2', N'0', N'opening', N'Bánh Mì Heo Quay Mỹ Liên', N'2008-07-24 21:59:47.0000000')
GO

INSERT INTO [dbo].[store] ([store_id], [address], [avatar], [balance], [created_at], [fcm_token], [phone], [rate], [review_count], [status], [store_name], [updated_at]) VALUES (N'21', N'310 Nơ Trang Long, Phường 13, Quận Bình Thạnh', N'https://tea-3.lozi.vn/v1/images/resized/banh-mi-thit-nuong-banh-mi-bien-tau-nguyen-trai-37-nguyen-trai-quan-1-tp-hcm-353-eatery-avatar-1625905593?w=640&type=s', N'86388402', N'2021-08-17 04:40:14.0000000', NULL, N'0986191748', N'1.9', N'0', N'opening', N'Bánh Mì Hà Nội', N'2009-01-17 16:33:40.0000000')
GO

INSERT INTO [dbo].[store] ([store_id], [address], [avatar], [balance], [created_at], [fcm_token], [phone], [rate], [review_count], [status], [store_name], [updated_at]) VALUES (N'22', N'23 Dương Quảng Hàm, Phường 7, Quận Gò Vấp', N'https://xebanhmithonhiky.com.vn/wp-content/uploads/2019/09/banh-mi-kep-xoi.jpg', N'86061915', N'2008-08-18 10:27:09.0000000', NULL, N'0919616907', N'4.1', N'0', N'opening', N'Bánh Mì & Xôi - Quốc Hương', N'2001-09-20 08:00:07.0000000')
GO

INSERT INTO [dbo].[store] ([store_id], [address], [avatar], [balance], [created_at], [fcm_token], [phone], [rate], [review_count], [status], [store_name], [updated_at]) VALUES (N'23', N'189 Trần Não, Phường Bình An, Quận 2', N'https://dulichvietnam.com.vn/vnt_upload/File/Image/quan_banh_mi_pha_lau_o_tphcm_3.jpg', N'81113558', N'2003-12-23 02:43:36.0000000', NULL, N'0961410372', N'3.2', N'0', N'opening', N'Bánh Mì Hoa Đất', N'2003-07-04 18:01:29.0000000')
GO

INSERT INTO [dbo].[store] ([store_id], [address], [avatar], [balance], [created_at], [fcm_token], [phone], [rate], [review_count], [status], [store_name], [updated_at]) VALUES (N'24', N'84 D5, Phường 25, Quận Bình Thạnh', N'https://d1sag4ddilekf6.cloudfront.net/compressed_webp/merchants/5-CYKJTVNCT3BKBE/hero/c31f5d5f6cb843c9bae3dcf81591c9fc_1673218703602230411.webp', N'78634719', N'2022-07-19 12:04:15.0000000', NULL, N'0932575262', N'3.5', N'0', N'opening', N'Bánh Mì PewPew - D5', N'2015-01-21 19:28:36.0000000')
GO

INSERT INTO [dbo].[store] ([store_id], [address], [avatar], [balance], [created_at], [fcm_token], [phone], [rate], [review_count], [status], [store_name], [updated_at]) VALUES (N'25', N'55 Quốc Hương, Phường Thảo Điền, Quận 2', N'https://www.cet.edu.vn/wp-content/uploads/2020/04/cach-lam-com-chien-ga-xoi-mo.jpg', N'43040036', N'2003-09-15 19:07:38.0000000', NULL, N'0900482052', N'2.4', N'0', N'opening', N'Cơm Gà Xối Mỡ Quốc Hương', N'2007-12-13 11:55:02.0000000')
GO

INSERT INTO [dbo].[store] ([store_id], [address], [avatar], [balance], [created_at], [fcm_token], [phone], [rate], [review_count], [status], [store_name], [updated_at]) VALUES (N'26', N'Khu An Phú, An Khánh, Phường Thảo Điền, Quận 2', N'https://gigamall.com.vn/data/2019/09/20/11312037_LOGO-LOTTERIA.png', N'60367851', N'2002-08-04 10:45:31.0000000', NULL, N'0978622418', N'3.1', N'0', N'opening', N'Lotteria - Metro An Phú', N'2022-07-12 06:23:36.0000000')
GO

INSERT INTO [dbo].[store] ([store_id], [address], [avatar], [balance], [created_at], [fcm_token], [phone], [rate], [review_count], [status], [store_name], [updated_at]) VALUES (N'27', N'TTTM Giga Mall, 240-242 Phạm Văn Đồng, Phường Hiệp Bình Chánh, TP Thủ Đức', N'https://gigamall.com.vn/data/2019/09/20/11491513_LOGO-McDonald_s.jpg', N'55465302', N'2010-01-16 09:09:01.0000000', NULL, N'0913167439', N'2.5', N'0', N'opening', N'McDonald''s - TTTM Giga Mall', N'2003-01-23 16:29:17.0000000')
GO

INSERT INTO [dbo].[store] ([store_id], [address], [avatar], [balance], [created_at], [fcm_token], [phone], [rate], [review_count], [status], [store_name], [updated_at]) VALUES (N'28', N'212 Nguyễn Gia Trí, Phường 25, Quận Bình Thạnh', N'https://images.foody.vn/res/g27/267851/prof/s640x400/foody-upload-api-foody-mobile-9-190620094449.jpg', N'16375449', N'2012-08-18 18:28:06.0000000', NULL, N'0987174792', N'1.1', N'0', N'opening', N'Bún Đậu Homemade - Nguyễn Gia Trí', N'2021-08-08 06:44:42.0000000')
GO

INSERT INTO [dbo].[store] ([store_id], [address], [avatar], [balance], [created_at], [fcm_token], [phone], [rate], [review_count], [status], [store_name], [updated_at]) VALUES (N'29', N'82 Ngô Quyền, Phường Hiệp Phú, Quận 10', N'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR-rtZHoIPkV9obntknz85KTpuWXNtZpGdWMg&usqp=CAU', N'73398224', N'2012-01-29 22:10:31.0000000', NULL, N'0970756410', N'2.9', N'0', N'opening', N'Trà Sữa Bee Lattea - Ngô Quyền', N'2012-08-24 05:39:11.0000000')
GO

INSERT INTO [dbo].[store] ([store_id], [address], [avatar], [balance], [created_at], [fcm_token], [phone], [rate], [review_count], [status], [store_name], [updated_at]) VALUES (N'30', N'489A Lê Văn Việt, Phường Tăng Nhơn Phú A, Quận 9, TP Thủ Đức', N'https://static.mservice.io/blogscontents/momo-upload-api-191209103804-637114846840219227.jpg', N'52837321', N'2011-04-25 22:00:50.0000000', NULL, N'0931048515', N'3.7', N'0', N'opening', N'Kumtean - Sinh Tố - Nước Ép - Trà', N'2011-05-23 14:13:03.0000000')
GO

SET IDENTITY_INSERT [dbo].[store] OFF
GO


-- ----------------------------
-- Table structure for store_account
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[store_account]') AND type IN ('U'))
	DROP TABLE [dbo].[store_account]
GO

CREATE TABLE [dbo].[store_account] (
  [email] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [password] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [status] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [updated_at] datetime2(7)  NULL,
  [store_id] bigint  NULL
)
GO

ALTER TABLE [dbo].[store_account] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of store_account
-- ----------------------------
INSERT INTO [dbo].[store_account] ([email], [password], [status], [updated_at], [store_id]) VALUES (N'akisolangtangphu@gmail.com', N'NieQminDE4Ggcewn98nKl3Jhgq7Smn3dLlQ1MyLPswq7njpt8qwsIP4jQ2MR1nhWTQyNMFkwV19g4tPQSBhNeQ==', N'activated', N'2005-01-23 04:21:06.0000000', N'7')
GO

INSERT INTO [dbo].[store_account] ([email], [password], [status], [updated_at], [store_id]) VALUES (N'banhminotranglong@gmail.com', N'NieQminDE4Ggcewn98nKl3Jhgq7Smn3dLlQ1MyLPswq7njpt8qwsIP4jQ2MR1nhWTQyNMFkwV19g4tPQSBhNeQ==', N'activated', N'2013-03-29 08:21:03.0000000', N'21')
GO

INSERT INTO [dbo].[store_account] ([email], [password], [status], [updated_at], [store_id]) VALUES (N'beelatteangoquyen@gmail.com', N'NieQminDE4Ggcewn98nKl3Jhgq7Smn3dLlQ1MyLPswq7njpt8qwsIP4jQ2MR1nhWTQyNMFkwV19g4tPQSBhNeQ==', N'activated', N'2012-08-19 18:24:58.0000000', N'29')
GO

INSERT INTO [dbo].[store_account] ([email], [password], [status], [updated_at], [store_id]) VALUES (N'bumstop385@mail.com', N'NieQminDE4Ggcewn98nKl3Jhgq7Smn3dLlQ1MyLPswq7njpt8qwsIP4jQ2MR1nhWTQyNMFkwV19g4tPQSBhNeQ==', N'activated', N'2020-09-30 00:52:50.0000000', N'1')
GO

INSERT INTO [dbo].[store_account] ([email], [password], [status], [updated_at], [store_id]) VALUES (N'comchaythoikemanthien@gmail.com', N'NieQminDE4Ggcewn98nKl3Jhgq7Smn3dLlQ1MyLPswq7njpt8qwsIP4jQ2MR1nhWTQyNMFkwV19g4tPQSBhNeQ==', N'activated', N'2019-08-13 21:22:41.0000000', N'2')
GO

INSERT INTO [dbo].[store_account] ([email], [password], [status], [updated_at], [store_id]) VALUES (N'comgaquochuong@gmail.com', N'NieQminDE4Ggcewn98nKl3Jhgq7Smn3dLlQ1MyLPswq7njpt8qwsIP4jQ2MR1nhWTQyNMFkwV19g4tPQSBhNeQ==', N'activated', N'2011-04-30 07:20:47.0000000', N'25')
GO

INSERT INTO [dbo].[store_account] ([email], [password], [status], [updated_at], [store_id]) VALUES (N'companyvovanngan@gmail.com', N'NieQminDE4Ggcewn98nKl3Jhgq7Smn3dLlQ1MyLPswq7njpt8qwsIP4jQ2MR1nhWTQyNMFkwV19g4tPQSBhNeQ==', N'activated', N'2008-04-02 13:33:42.0000000', N'12')
GO

INSERT INTO [dbo].[store_account] ([email], [password], [status], [updated_at], [store_id]) VALUES (N'denhatlevanviet@gmail.com', N'NieQminDE4Ggcewn98nKl3Jhgq7Smn3dLlQ1MyLPswq7njpt8qwsIP4jQ2MR1nhWTQyNMFkwV19g4tPQSBhNeQ==', N'activated', N'2003-04-15 07:57:03.0000000', N'5')
GO

INSERT INTO [dbo].[store_account] ([email], [password], [status], [updated_at], [store_id]) VALUES (N'dominolevanviet@gmail.com', N'NieQminDE4Ggcewn98nKl3Jhgq7Smn3dLlQ1MyLPswq7njpt8qwsIP4jQ2MR1nhWTQyNMFkwV19g4tPQSBhNeQ==', N'activated', N'2001-03-27 06:20:28.0000000', N'13')
GO

INSERT INTO [dbo].[store_account] ([email], [password], [status], [updated_at], [store_id]) VALUES (N'hoadattrannao@gmail.com', N'NieQminDE4Ggcewn98nKl3Jhgq7Smn3dLlQ1MyLPswq7njpt8qwsIP4jQ2MR1nhWTQyNMFkwV19g4tPQSBhNeQ==', N'activated', N'2010-10-06 07:16:32.0000000', N'23')
GO

INSERT INTO [dbo].[store_account] ([email], [password], [status], [updated_at], [store_id]) VALUES (N'homemadenguyengiatri@gmail.com', N'NieQminDE4Ggcewn98nKl3Jhgq7Smn3dLlQ1MyLPswq7njpt8qwsIP4jQ2MR1nhWTQyNMFkwV19g4tPQSBhNeQ==', N'activated', N'2005-06-09 12:40:35.0000000', N'28')
GO

INSERT INTO [dbo].[store_account] ([email], [password], [status], [updated_at], [store_id]) VALUES (N'juicehouselaxuanoai@gmail.com', N'NieQminDE4Ggcewn98nKl3Jhgq7Smn3dLlQ1MyLPswq7njpt8qwsIP4jQ2MR1nhWTQyNMFkwV19g4tPQSBhNeQ==', N'activated', N'2020-01-14 02:28:41.0000000', N'14')
GO

INSERT INTO [dbo].[store_account] ([email], [password], [status], [updated_at], [store_id]) VALUES (N'kfclevanviet@gmail.com', N'QHc0xBrI9l4z43cMLQV6c0xQFU6bvdWq7HWy1kv1H+z02sL2cgU0vAAS5xkh02XulY0ez/bwMZb+u+As/VdYSA==', N'activated', N'2014-02-27 01:45:16.0000000', N'3')
GO

INSERT INTO [dbo].[store_account] ([email], [password], [status], [updated_at], [store_id]) VALUES (N'kumteanlevanviet@gmail.com', N'NieQminDE4Ggcewn98nKl3Jhgq7Smn3dLlQ1MyLPswq7njpt8qwsIP4jQ2MR1nhWTQyNMFkwV19g4tPQSBhNeQ==', N'activated', N'2017-02-23 11:16:51.0000000', N'30')
GO

INSERT INTO [dbo].[store_account] ([email], [password], [status], [updated_at], [store_id]) VALUES (N'laithieunguyenkhuyen@gmail.com', N'FvVAFxNevuAM/xEwd/392VphXgsG3rskHHnGEubWLfb4vesjId7OYi1fJsNwIgytU9D1dkaXLvYvbVkZwQxOsA==', N'activated', N'2003-04-12 12:16:40.0000000', N'18')
GO

INSERT INTO [dbo].[store_account] ([email], [password], [status], [updated_at], [store_id]) VALUES (N'lotteriaankhanh@gmail.com', N'NieQminDE4Ggcewn98nKl3Jhgq7Smn3dLlQ1MyLPswq7njpt8qwsIP4jQ2MR1nhWTQyNMFkwV19g4tPQSBhNeQ==', N'activated', N'2004-12-22 17:05:20.0000000', N'26')
GO

INSERT INTO [dbo].[store_account] ([email], [password], [status], [updated_at], [store_id]) VALUES (N'mcdonaldphamvandong@gmail.com', N'NieQminDE4Ggcewn98nKl3Jhgq7Smn3dLlQ1MyLPswq7njpt8qwsIP4jQ2MR1nhWTQyNMFkwV19g4tPQSBhNeQ==', N'activated', N'2019-03-04 18:10:06.0000000', N'27')
GO

INSERT INTO [dbo].[store_account] ([email], [password], [status], [updated_at], [store_id]) VALUES (N'mrchanhtamha@gmail.com', N'NieQminDE4Ggcewn98nKl3Jhgq7Smn3dLlQ1MyLPswq7njpt8qwsIP4jQ2MR1nhWTQyNMFkwV19g4tPQSBhNeQ==', N'activated', N'2012-11-23 04:08:42.0000000', N'15')
GO

INSERT INTO [dbo].[store_account] ([email], [password], [status], [updated_at], [store_id]) VALUES (N'mylienvovantan@gmail.com', N'NieQminDE4Ggcewn98nKl3Jhgq7Smn3dLlQ1MyLPswq7njpt8qwsIP4jQ2MR1nhWTQyNMFkwV19g4tPQSBhNeQ==', N'activated', N'2001-06-21 20:42:48.0000000', N'20')
GO

INSERT INTO [dbo].[store_account] ([email], [password], [status], [updated_at], [store_id]) VALUES (N'nong449@gmail.com', N'NieQminDE4Ggcewn98nKl3Jhgq7Smn3dLlQ1MyLPswq7njpt8qwsIP4jQ2MR1nhWTQyNMFkwV19g4tPQSBhNeQ==', N'activated', N'2019-11-07 18:47:43.0000000', N'8')
GO

INSERT INTO [dbo].[store_account] ([email], [password], [status], [updated_at], [store_id]) VALUES (N'pewpewD5@outlook.com', N'NieQminDE4Ggcewn98nKl3Jhgq7Smn3dLlQ1MyLPswq7njpt8qwsIP4jQ2MR1nhWTQyNMFkwV19g4tPQSBhNeQ==', N'activated', N'2013-06-22 03:35:16.0000000', N'24')
GO

INSERT INTO [dbo].[store_account] ([email], [password], [status], [updated_at], [store_id]) VALUES (N'phohotaykhavancan@gmail.com', N'NieQminDE4Ggcewn98nKl3Jhgq7Smn3dLlQ1MyLPswq7njpt8qwsIP4jQ2MR1nhWTQyNMFkwV19g4tPQSBhNeQ==', N'activated', N'2001-02-26 23:10:15.0000000', N'19')
GO

INSERT INTO [dbo].[store_account] ([email], [password], [status], [updated_at], [store_id]) VALUES (N'phoquehuongnguyenvantang@gmail.com', N'NieQminDE4Ggcewn98nKl3Jhgq7Smn3dLlQ1MyLPswq7njpt8qwsIP4jQ2MR1nhWTQyNMFkwV19g4tPQSBhNeQ==', N'activated', N'2005-08-10 10:45:50.0000000', N'16')
GO

INSERT INTO [dbo].[store_account] ([email], [password], [status], [updated_at], [store_id]) VALUES (N'phoxuasonghanh@gmail.com', N'NieQminDE4Ggcewn98nKl3Jhgq7Smn3dLlQ1MyLPswq7njpt8qwsIP4jQ2MR1nhWTQyNMFkwV19g4tPQSBhNeQ==', N'activated', N'2007-02-02 05:07:51.0000000', N'17')
GO

INSERT INTO [dbo].[store_account] ([email], [password], [status], [updated_at], [store_id]) VALUES (N'phucloctholevanviet@gmail.com', N'NieQminDE4Ggcewn98nKl3Jhgq7Smn3dLlQ1MyLPswq7njpt8qwsIP4jQ2MR1nhWTQyNMFkwV19g4tPQSBhNeQ==', N'activated', N'2004-08-29 08:16:27.0000000', N'6')
GO

INSERT INTO [dbo].[store_account] ([email], [password], [status], [updated_at], [store_id]) VALUES (N'phuclongkhavancan@gmail.com', N'NieQminDE4Ggcewn98nKl3Jhgq7Smn3dLlQ1MyLPswq7njpt8qwsIP4jQ2MR1nhWTQyNMFkwV19g4tPQSBhNeQ==', N'activated', N'2016-05-11 13:55:15.0000000', N'9')
GO

INSERT INTO [dbo].[store_account] ([email], [password], [status], [updated_at], [store_id]) VALUES (N'pizzahutquangtrung@gmail.com', N'NieQminDE4Ggcewn98nKl3Jhgq7Smn3dLlQ1MyLPswq7njpt8qwsIP4jQ2MR1nhWTQyNMFkwV19g4tPQSBhNeQ==', N'activated', N'2003-08-04 18:07:15.0000000', N'11')
GO

INSERT INTO [dbo].[store_account] ([email], [password], [status], [updated_at], [store_id]) VALUES (N'quochuongduongquangham@gmail.com', N'NieQminDE4Ggcewn98nKl3Jhgq7Smn3dLlQ1MyLPswq7njpt8qwsIP4jQ2MR1nhWTQyNMFkwV19g4tPQSBhNeQ==', N'activated', N'2014-01-02 05:08:26.0000000', N'22')
GO

INSERT INTO [dbo].[store_account] ([email], [password], [status], [updated_at], [store_id]) VALUES (N'shilindoanket@gmail.com', N'NieQminDE4Ggcewn98nKl3Jhgq7Smn3dLlQ1MyLPswq7njpt8qwsIP4jQ2MR1nhWTQyNMFkwV19g4tPQSBhNeQ==', N'activated', N'2016-04-29 13:43:01.0000000', N'4')
GO

INSERT INTO [dbo].[store_account] ([email], [password], [status], [updated_at], [store_id]) VALUES (N'tocotocotanlap@gmail.com', N'NieQminDE4Ggcewn98nKl3Jhgq7Smn3dLlQ1MyLPswq7njpt8qwsIP4jQ2MR1nhWTQyNMFkwV19g4tPQSBhNeQ==', N'activated', N'2012-03-17 13:12:50.0000000', N'10')
GO


-- ----------------------------
-- Table structure for user_account
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[user_account]') AND type IN ('U'))
	DROP TABLE [dbo].[user_account]
GO

CREATE TABLE [dbo].[user_account] (
  [email] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [otp] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [otp_created_at] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [otp_last_attempt] datetime2(7)  NULL,
  [otp_wrong_count] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [password] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [status] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [updated_at] datetime2(7)  NULL,
  [user_id] bigint  NULL
)
GO

ALTER TABLE [dbo].[user_account] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of user_account
-- ----------------------------
INSERT INTO [dbo].[user_account] ([email], [otp], [otp_created_at], [otp_last_attempt], [otp_wrong_count], [password], [status], [updated_at], [user_id]) VALUES (N'a@gmail.com', NULL, NULL, NULL, NULL, N'PJkJr+wlNU1VHa4hWQuybjjVPyFzuNPcPu5MBH56scHri4UQPjvnumE7MbtcnDYhTcnxSkL9ei/bhIVrylxEwg==', N'activated', N'2023-04-07 00:04:15.3230000', N'4')
GO

INSERT INTO [dbo].[user_account] ([email], [otp], [otp_created_at], [otp_last_attempt], [otp_wrong_count], [password], [status], [updated_at], [user_id]) VALUES (N'baonguyen@gmail.com', NULL, NULL, NULL, NULL, N'PJkJr+wlNU1VHa4hWQuybjjVPyFzuNPcPu5MBH56scHri4UQPjvnumE7MbtcnDYhTcnxSkL9ei/bhIVrylxEwg==', N'activated', N'2023-05-17 10:18:21.8340000', N'9')
GO

INSERT INTO [dbo].[user_account] ([email], [otp], [otp_created_at], [otp_last_attempt], [otp_wrong_count], [password], [status], [updated_at], [user_id]) VALUES (N'didi@gmail.com', NULL, NULL, NULL, NULL, N'QnElluMvyEarAbza0OzrHluxf7WU8R0cdwLyK0yDBB355q3YmHqJUYqbxlaA0XK4SNKjDvr8AWkA3ynaBpEMyw==', N'activated', N'2023-05-13 01:37:34.4760000', N'7')
GO

INSERT INTO [dbo].[user_account] ([email], [otp], [otp_created_at], [otp_last_attempt], [otp_wrong_count], [password], [status], [updated_at], [user_id]) VALUES (N'minhtu@gmail.com', NULL, NULL, NULL, NULL, N'e2rXmzRvtpUSdTQ5SOE8G068qCpUUqbF0VaEN38JbKknUGojqEfm4EYGE5ljGxb8KCDIsOAtDqh6paIDp3wqfg==', N'activated', N'2023-04-05 13:25:09.0940000', N'2')
GO

INSERT INTO [dbo].[user_account] ([email], [otp], [otp_created_at], [otp_last_attempt], [otp_wrong_count], [password], [status], [updated_at], [user_id]) VALUES (N'minhtu1@gmail.com', NULL, NULL, NULL, NULL, N'NieQminDE4Ggcewn98nKl3Jhgq7Smn3dLlQ1MyLPswq7njpt8qwsIP4jQ2MR1nhWTQyNMFkwV19g4tPQSBhNeQ==', N'activated', N'2023-05-17 10:16:49.7940000', N'8')
GO

INSERT INTO [dbo].[user_account] ([email], [otp], [otp_created_at], [otp_last_attempt], [otp_wrong_count], [password], [status], [updated_at], [user_id]) VALUES (N'nguyenbao@gmail.com', NULL, NULL, NULL, NULL, N'mTYGcsS23Mq3EvR6qfIJwE8rlsampSliaKD5+lSovxKVrbQ0/aXDveXW20sCWI4D4+yoYAjhDv5crIWZRjdG9w==', N'activated', N'2023-05-18 09:57:17.3090000', N'11')
GO

INSERT INTO [dbo].[user_account] ([email], [otp], [otp_created_at], [otp_last_attempt], [otp_wrong_count], [password], [status], [updated_at], [user_id]) VALUES (N'nguyenminhtu@gmail.com', NULL, NULL, NULL, NULL, N'7npG/aAB6OhcF13ezquvSMaRSKp458xJORY77kT8Q0JJB5HzyA1x8WgUsg0+5I6BQkKQgZ8v3nsZnpArmL3TXg==', N'activated', N'2023-05-17 17:32:08.3400000', N'10')
GO

INSERT INTO [dbo].[user_account] ([email], [otp], [otp_created_at], [otp_last_attempt], [otp_wrong_count], [password], [status], [updated_at], [user_id]) VALUES (N'quocbao@gmail.com', NULL, NULL, NULL, NULL, N'PJkJr+wlNU1VHa4hWQuybjjVPyFzuNPcPu5MBH56scHri4UQPjvnumE7MbtcnDYhTcnxSkL9ei/bhIVrylxEwg==', N'activated', N'2023-05-08 11:33:18.1360000', N'6')
GO

INSERT INTO [dbo].[user_account] ([email], [otp], [otp_created_at], [otp_last_attempt], [otp_wrong_count], [password], [status], [updated_at], [user_id]) VALUES (N'tuyetvi@gmail.com', NULL, NULL, NULL, NULL, N'A5LHu4Y8AjqkZjgK1IR2Wjr1tyakhXg9Wy8SVwy/U6YLRGRvJsbFDcAUcbil18OMCVz0lcQWtAuuzV+0rcf/ng==', N'activated', N'2023-04-05 13:24:52.9120000', N'1')
GO


-- ----------------------------
-- Table structure for user_address
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[user_address]') AND type IN ('U'))
	DROP TABLE [dbo].[user_address]
GO

CREATE TABLE [dbo].[user_address] (
  [user_address_id] bigint  IDENTITY(1,1) NOT NULL,
  [created_at] datetime2(7)  NULL,
  [latitude] float(53)  NOT NULL,
  [longitude] float(53)  NOT NULL,
  [street_address] nvarchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [updated_at] datetime2(7)  NULL,
  [user_profile_user_id] bigint  NULL,
  [is_deleted] bit  NOT NULL
)
GO

ALTER TABLE [dbo].[user_address] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of user_address
-- ----------------------------
SET IDENTITY_INSERT [dbo].[user_address] ON
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'6', N'2023-05-09 00:11:52.6900000', N'100', N'100', N'1 Võ Van Ngân', N'2023-05-16 16:14:37.2780000', N'2', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'7', N'2023-05-09 01:07:02.8680000', N'100', N'100', N'484A Lê Văn Việt', N'2023-05-16 16:49:10.9860000', N'1', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'8', N'2023-05-16 14:54:17.4040000', N'100', N'100', N'484 Lê Văn Việt', N'2023-05-16 17:25:11.2790000', N'2', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'9', N'2023-05-16 14:58:51.7860000', N'100', N'100', N'2 Võ Văn Việt', N'2023-05-16 15:54:19.8950000', N'2', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'10', N'2023-05-16 15:03:01.0870000', N'100', N'100', N'3 Lê Văn Ngân', N'2023-05-16 15:52:13.5160000', N'2', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'11', N'2023-05-16 15:08:01.8170000', N'100', N'100', N'4 Võ Văn Việt', N'2023-05-16 15:52:06.4310000', N'2', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'12', N'2023-05-16 15:15:08.5170000', N'100', N'100', N'5 Lê Văn Ngân', N'2023-05-16 15:32:54.8190000', N'2', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'13', N'2023-05-16 15:30:33.9360000', N'100', N'100', N'6 Võ Thị Ngân', N'2023-05-16 15:39:17.8400000', N'2', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'14', N'2023-05-16 15:39:33.7190000', N'100', N'100', N'5 Võ Thị Việt', N'2023-05-16 15:50:25.0970000', N'2', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'15', N'2023-05-16 15:59:56.7940000', N'100', N'100', N'18 Lê Thị Việt', N'2023-05-16 16:08:55.7860000', N'2', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'16', N'2023-05-16 16:02:41.3620000', N'100', N'100', N'19 Lê Văn Ngân', N'2023-05-16 16:03:27.6450000', N'2', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'17', N'2023-05-16 16:09:26.2670000', N'100', N'100', N'19 Lê Quốc Việt', N'2023-05-16 17:25:08.3620000', N'2', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'18', N'2023-05-16 16:12:19.6640000', N'100', N'100', N'20 Lê Quốc Ngân', N'2023-05-16 17:25:07.4830000', N'2', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'19', N'2023-05-16 16:14:54.3330000', N'100', N'100', N'21 Võ Quốc Ngân', N'2023-05-16 16:15:09.5190000', N'2', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'20', N'2023-05-16 16:15:30.4230000', N'100', N'100', N'22 Võ Quốc Việt', N'2023-05-16 16:18:40.6800000', N'2', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'21', N'2023-05-16 16:15:43.3920000', N'100', N'100', N'22 Võ Quốc Việt 2', N'2023-05-16 16:18:26.9680000', N'2', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'22', N'2023-05-16 16:18:48.9170000', N'100', N'100', N'12345', N'2023-05-16 17:17:28.7620000', N'2', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'23', N'2023-05-16 16:20:34.8200000', N'100', N'100', N'12345', N'2023-05-16 16:20:44.5340000', N'2', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'24', N'2023-05-16 16:20:56.8160000', N'100', N'100', N'gị', N'2023-05-16 16:21:12.8000000', N'2', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'25', N'2023-05-16 16:29:04.6190000', N'100', N'100', N'123', N'2023-05-16 16:29:20.6820000', N'1', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'26', N'2023-05-16 16:29:35.7540000', N'100', N'100', N'1234', N'2023-05-16 16:42:28.4570000', N'1', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'27', N'2023-05-16 16:32:59.3390000', N'100', N'100', N'789', N'2023-05-16 16:42:28.0330000', N'1', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'28', N'2023-05-16 16:35:33.2130000', N'100', N'100', N'89', N'2023-05-16 16:42:27.4860000', N'1', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'29', N'2023-05-16 16:36:57.4850000', N'100', N'100', N'899', N'2023-05-16 16:37:04.0550000', N'1', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'30', N'2023-05-16 16:37:46.0310000', N'100', N'100', N'888', N'2023-05-16 16:42:26.9630000', N'1', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'31', N'2023-05-16 16:41:52.7850000', N'100', N'100', N'78', N'2023-05-16 16:41:56.3870000', N'1', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'32', N'2023-05-16 16:41:53.2050000', N'100', N'100', N'78', N'2023-05-16 16:42:26.4130000', N'1', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'33', N'2023-05-16 16:42:17.5780000', N'100', N'100', N'new', N'2023-05-16 16:49:13.6550000', N'1', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'34', N'2023-05-16 16:42:34.9760000', N'100', N'100', N'hi', N'2023-05-16 16:42:37.1410000', N'1', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'35', N'2023-05-16 16:42:44.9840000', N'100', N'100', N'hii', N'2023-05-16 16:49:21.2780000', N'1', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'36', N'2023-05-16 16:43:40.5350000', N'100', N'100', N'89', N'2023-05-16 16:49:24.4520000', N'1', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'37', N'2023-05-16 16:43:56.6220000', N'100', N'100', N'89', N'2023-05-16 16:50:32.4450000', N'1', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'38', N'2023-05-16 16:44:39.3130000', N'100', N'100', N'77', N'2023-05-16 16:48:56.5420000', N'1', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'39', N'2023-05-16 16:46:54.2160000', N'100', N'100', N'uu', N'2023-05-16 16:50:38.5820000', N'1', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'40', N'2023-05-16 16:50:02.6720000', N'100', N'100', N'ii', N'2023-05-16 16:50:06.5250000', N'1', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'41', N'2023-05-16 16:50:18.0080000', N'100', N'100', N'484 Lê Văn Việt', N'2023-05-16 16:50:21.6530000', N'1', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'42', N'2023-05-16 16:50:25.7930000', N'100', N'100', N'484 Lê Văn Việttt', N'2023-05-16 16:50:42.9450000', N'1', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'43', N'2023-05-16 16:51:14.4710000', N'100', N'100', N'484 Lê Văn Việt', N'2023-05-16 16:51:18.2540000', N'1', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'44', N'2023-05-16 16:51:19.9190000', N'100', N'100', N'484 Lê Văn Việt', N'2023-05-16 16:51:29.9800000', N'1', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'45', N'2023-05-16 16:51:21.9660000', N'100', N'100', N'484 Lê Văn Việt', N'2023-05-16 16:51:29.5630000', N'1', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'46', N'2023-05-16 16:51:23.0880000', N'100', N'100', N'484 Lê Văn Việt', N'2023-05-16 16:51:29.1050000', N'1', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'47', N'2023-05-16 16:51:23.2790000', N'100', N'100', N'484 Lê Văn Việt', N'2023-05-16 16:51:28.7080000', N'1', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'48', N'2023-05-16 16:51:23.4380000', N'100', N'100', N'484 Lê Văn Việt', N'2023-05-16 16:51:28.4080000', N'1', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'49', N'2023-05-16 16:51:23.7800000', N'100', N'100', N'484 Lê Văn Việt', N'2023-05-16 16:51:27.9520000', N'1', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'50', N'2023-05-16 16:51:23.9670000', N'100', N'100', N'484 Lê Văn Việt', N'2023-05-16 16:51:27.5800000', N'1', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'51', N'2023-05-16 16:51:24.1330000', N'100', N'100', N'484 Lê Văn Việt', N'2023-05-16 16:51:27.1760000', N'1', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'52', N'2023-05-16 16:51:24.6940000', N'100', N'100', N'484 Lê Văn Việt', N'2023-05-16 16:51:26.5440000', N'1', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'53', N'2023-05-16 16:51:34.8330000', N'100', N'100', N'484 Lê Văn Việt', N'2023-05-16 16:51:34.8330000', N'1', N'0')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'54', N'2023-05-16 16:51:52.8710000', N'100', N'100', N'333', N'2023-05-16 16:51:54.6020000', N'1', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'55', N'2023-05-16 17:17:38.4120000', N'100', N'100', N'123', N'2023-05-16 17:17:46.4330000', N'2', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'56', N'2023-05-16 17:27:02.8630000', N'100', N'100', N'1 qwe', N'2023-05-16 17:27:05.4330000', N'2', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'57', N'2023-05-16 17:27:14.9220000', N'100', N'100', N'235', N'2023-05-16 17:27:22.5860000', N'2', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'58', N'2023-05-16 17:27:19.8020000', N'100', N'100', N'235 hff', N'2023-05-16 21:34:01.8590000', N'2', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'59', N'2023-05-16 19:30:06.1150000', N'100', N'100', N'123', N'2023-05-16 21:34:03.7040000', N'2', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'60', N'2023-05-16 19:30:07.2360000', N'100', N'100', N'123', N'2023-05-16 21:34:11.9100000', N'2', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'61', N'2023-05-16 19:30:16.3040000', N'100', N'100', N'', N'2023-05-16 19:30:18.9210000', N'2', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'62', N'2023-05-16 19:33:59.5970000', N'100', N'100', N'1234', N'2023-05-16 21:43:09.0080000', N'2', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'63', N'2023-05-16 21:36:27.6570000', N'100', N'100', N'484 Lê Văn Việt', N'2023-05-16 21:36:27.6570000', N'2', N'0')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'64', N'2023-05-16 21:58:03.7030000', N'100', N'100', N'1 Võ Văn Ngân, Linh Chiểu, Thủ Đức', N'2023-05-16 21:58:03.7030000', N'2', N'0')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'65', N'2023-05-16 23:53:12.4070000', N'100', N'100', N'484 Lê Văn Ngân', N'2023-05-17 11:00:11.4060000', N'6', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'66', N'2023-05-17 00:09:08.9880000', N'100', N'100', N'1 Võ Văn Việt', N'2023-05-17 11:00:21.4270000', N'6', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'67', N'2023-05-17 00:31:52.9130000', N'100', N'100', N'484A Lê Văn Việt', N'2023-05-17 00:31:52.9130000', N'7', N'0')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'68', N'2023-05-17 10:56:10.0950000', N'100', N'100', N'484A Lê Văn Việt, thành phố Thủ Đức', N'2023-05-17 10:56:10.0950000', N'9', N'0')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'69', N'2023-05-17 10:56:38.1490000', N'100', N'100', N'1 Võ Văn Ngân, quận 9, thành phố Hồ Chí Minh', N'2023-05-17 10:56:38.1490000', N'9', N'0')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'70', N'2023-05-17 11:00:28.0480000', N'100', N'100', N'123', N'2023-05-17 11:00:35.3420000', N'6', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'71', N'2023-05-17 11:00:31.2060000', N'100', N'100', N'456', N'2023-05-17 11:00:40.3640000', N'6', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'72', N'2023-05-17 11:07:38.9490000', N'100', N'100', N'484A Lê Văn Việt, thành phố Thủ Đức', N'2023-05-17 11:07:38.9490000', N'6', N'0')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'73', N'2023-05-17 11:08:00.2490000', N'100', N'100', N'1 Võ Văn Ngân, quận 9, thành phố Hồ Chí Minh', N'2023-05-17 11:08:00.2490000', N'6', N'0')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'74', N'2023-05-17 11:19:51.8380000', N'100', N'100', N'484 Lê Văn Việt', N'2023-05-17 11:19:51.8380000', N'8', N'0')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'75', N'2023-05-17 11:19:57.3920000', N'100', N'100', N'1 Võ Văn Ngân', N'2023-05-17 11:23:40.9000000', N'8', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'76', N'2023-05-17 11:20:13.6230000', N'100', N'100', N'Ở nơi nào đó trên hành tinh này', N'2023-05-17 11:20:28.5070000', N'8', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'77', N'2023-05-17 11:20:24.5360000', N'100', N'100', N'Mặt trăng', N'2023-05-17 11:20:37.9680000', N'8', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'78', N'2023-05-17 11:22:36.4080000', N'100', N'100', N'1 Lê Văn Việt', N'2023-05-17 11:22:52.3140000', N'8', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'79', N'2023-05-17 11:22:43.4840000', N'100', N'100', N'2 Lê Văn Việt', N'2023-05-17 11:22:53.4850000', N'8', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'80', N'2023-05-17 11:22:48.7450000', N'100', N'100', N'3 Lê Văn Việt', N'2023-05-17 11:23:07.3670000', N'8', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'81', N'2023-05-17 11:23:15.5400000', N'100', N'100', N'1 ', N'2023-05-17 11:23:30.3090000', N'8', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'82', N'2023-05-17 11:23:17.0570000', N'100', N'100', N'2', N'2023-05-17 11:23:39.9800000', N'8', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'83', N'2023-05-17 11:23:18.4830000', N'100', N'100', N'3', N'2023-05-17 11:23:48.3880000', N'8', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'84', N'2023-05-17 11:23:20.5470000', N'100', N'100', N'4', N'2023-05-17 11:23:49.1280000', N'8', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'85', N'2023-05-17 11:23:59.0050000', N'100', N'100', N'1 Võ Văn Ngân', N'2023-05-17 11:23:59.0050000', N'8', N'0')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'86', N'2023-05-17 11:25:34.2290000', N'100', N'100', N'1', N'2023-05-17 11:25:41.2380000', N'8', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'87', N'2023-05-17 11:25:35.1100000', N'100', N'100', N'2', N'2023-05-17 11:25:50.4200000', N'8', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'88', N'2023-05-17 11:25:36.3980000', N'100', N'100', N'3', N'2023-05-17 11:32:35.2580000', N'8', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'89', N'2023-05-17 11:25:37.6090000', N'100', N'100', N'4', N'2023-05-17 11:30:01.7610000', N'8', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'90', N'2023-05-17 11:32:30.7280000', N'100', N'100', N'4', N'2023-05-17 11:32:39.0400000', N'8', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'91', N'2023-05-17 11:32:31.5450000', N'100', N'100', N'5', N'2023-05-17 11:32:37.7720000', N'8', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'92', N'2023-05-17 11:32:32.7200000', N'100', N'100', N'6', N'2023-05-17 11:32:40.2490000', N'8', N'1')
GO

INSERT INTO [dbo].[user_address] ([user_address_id], [created_at], [latitude], [longitude], [street_address], [updated_at], [user_profile_user_id], [is_deleted]) VALUES (N'93', N'2023-05-18 10:01:04.4980000', N'100', N'100', N'484 Võ Văn Ngân', N'2023-05-18 10:01:04.4980000', N'6', N'0')
GO

SET IDENTITY_INSERT [dbo].[user_address] OFF
GO


-- ----------------------------
-- Table structure for user_favorites
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[user_favorites]') AND type IN ('U'))
	DROP TABLE [dbo].[user_favorites]
GO

CREATE TABLE [dbo].[user_favorites] (
  [user_favorites_id] bigint  IDENTITY(1,1) NOT NULL,
  [store_store_id] bigint  NULL,
  [user_profile_user_id] bigint  NULL
)
GO

ALTER TABLE [dbo].[user_favorites] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of user_favorites
-- ----------------------------
SET IDENTITY_INSERT [dbo].[user_favorites] ON
GO

INSERT INTO [dbo].[user_favorites] ([user_favorites_id], [store_store_id], [user_profile_user_id]) VALUES (N'14', N'18', N'2')
GO

INSERT INTO [dbo].[user_favorites] ([user_favorites_id], [store_store_id], [user_profile_user_id]) VALUES (N'15', N'18', N'2')
GO

INSERT INTO [dbo].[user_favorites] ([user_favorites_id], [store_store_id], [user_profile_user_id]) VALUES (N'23', N'3', N'6')
GO

SET IDENTITY_INSERT [dbo].[user_favorites] OFF
GO


-- ----------------------------
-- Table structure for user_order
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[user_order]') AND type IN ('U'))
	DROP TABLE [dbo].[user_order]
GO

CREATE TABLE [dbo].[user_order] (
  [order_id] bigint  IDENTITY(1,1) NOT NULL,
  [amount] float(53)  NULL,
  [created_at] datetime2(7)  NULL,
  [service_fee] float(53)  NULL,
  [ship_fee] float(53)  NULL,
  [status] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [updated_at] datetime2(7)  NULL,
  [coupon_coupon_id] int  NULL,
  [delivery_man_delivery_man_id] bigint  NULL,
  [store_store_id] bigint  NULL,
  [user_address_user_address_id] bigint  NULL,
  [user_profile_user_id] bigint  NULL,
  [discount_amount] float(53)  NULL
)
GO

ALTER TABLE [dbo].[user_order] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of user_order
-- ----------------------------
SET IDENTITY_INSERT [dbo].[user_order] ON
GO

INSERT INTO [dbo].[user_order] ([order_id], [amount], [created_at], [service_fee], [ship_fee], [status], [updated_at], [coupon_coupon_id], [delivery_man_delivery_man_id], [store_store_id], [user_address_user_address_id], [user_profile_user_id], [discount_amount]) VALUES (N'43', N'94499.9998435378', N'2023-05-17 00:09:02.0910000', N'2000', N'15000', N'success', N'2023-05-17 00:09:02.0910000', N'10', N'1', N'3', N'63', N'2', N'10500.0001564622')
GO

INSERT INTO [dbo].[user_order] ([order_id], [amount], [created_at], [service_fee], [ship_fee], [status], [updated_at], [coupon_coupon_id], [delivery_man_delivery_man_id], [store_store_id], [user_address_user_address_id], [user_profile_user_id], [discount_amount]) VALUES (N'44', N'210000', N'2023-05-17 00:10:01.3890000', N'2000', N'15000', N'success', N'2023-05-17 00:10:01.3890000', NULL, N'1', N'3', N'65', N'6', N'0')
GO

INSERT INTO [dbo].[user_order] ([order_id], [amount], [created_at], [service_fee], [ship_fee], [status], [updated_at], [coupon_coupon_id], [delivery_man_delivery_man_id], [store_store_id], [user_address_user_address_id], [user_profile_user_id], [discount_amount]) VALUES (N'45', N'180000', N'2023-05-11 02:32:27.2220000', N'2000', N'15000', N'success', N'2023-05-14 00:32:27.2220000', NULL, N'1', N'18', N'67', N'7', N'0')
GO

INSERT INTO [dbo].[user_order] ([order_id], [amount], [created_at], [service_fee], [ship_fee], [status], [updated_at], [coupon_coupon_id], [delivery_man_delivery_man_id], [store_store_id], [user_address_user_address_id], [user_profile_user_id], [discount_amount]) VALUES (N'46', N'240000', N'2023-05-12 00:36:39.2270000', N'2000', N'15000', N'success', N'2023-05-15 00:36:39.2270000', N'5', N'1', N'18', N'67', N'7', N'50000')
GO

INSERT INTO [dbo].[user_order] ([order_id], [amount], [created_at], [service_fee], [ship_fee], [status], [updated_at], [coupon_coupon_id], [delivery_man_delivery_man_id], [store_store_id], [user_address_user_address_id], [user_profile_user_id], [discount_amount]) VALUES (N'47', N'345000', N'2023-05-17 01:00:10.2000000', N'2000', N'15000', N'success', N'2023-05-17 01:00:10.2000000', NULL, N'1', N'3', N'63', N'2', N'0')
GO

INSERT INTO [dbo].[user_order] ([order_id], [amount], [created_at], [service_fee], [ship_fee], [status], [updated_at], [coupon_coupon_id], [delivery_man_delivery_man_id], [store_store_id], [user_address_user_address_id], [user_profile_user_id], [discount_amount]) VALUES (N'48', N'105000', N'2023-05-17 01:14:07.4030000', N'2000', N'15000', N'success', N'2023-05-17 01:14:07.4030000', NULL, N'1', N'3', N'63', N'2', N'0')
GO

INSERT INTO [dbo].[user_order] ([order_id], [amount], [created_at], [service_fee], [ship_fee], [status], [updated_at], [coupon_coupon_id], [delivery_man_delivery_man_id], [store_store_id], [user_address_user_address_id], [user_profile_user_id], [discount_amount]) VALUES (N'49', N'51199.9998092651', N'2023-05-17 01:30:26.4100000', N'2000', N'15000', N'success', N'2023-05-17 01:30:26.4100000', N'11', N'1', N'3', N'63', N'2', N'12800.0001907349')
GO

INSERT INTO [dbo].[user_order] ([order_id], [amount], [created_at], [service_fee], [ship_fee], [status], [updated_at], [coupon_coupon_id], [delivery_man_delivery_man_id], [store_store_id], [user_address_user_address_id], [user_profile_user_id], [discount_amount]) VALUES (N'50', N'399000', N'2023-05-17 09:02:29.3020000', N'2000', N'15000', N'success', N'2023-05-17 09:02:29.3020000', NULL, N'1', N'3', N'63', N'2', N'0')
GO

INSERT INTO [dbo].[user_order] ([order_id], [amount], [created_at], [service_fee], [ship_fee], [status], [updated_at], [coupon_coupon_id], [delivery_man_delivery_man_id], [store_store_id], [user_address_user_address_id], [user_profile_user_id], [discount_amount]) VALUES (N'51', N'240000', N'2023-05-13 09:40:23.7890000', N'2000', N'15000', N'success', N'2023-05-17 09:40:23.7890000', NULL, N'1', N'18', N'63', N'2', N'0')
GO

INSERT INTO [dbo].[user_order] ([order_id], [amount], [created_at], [service_fee], [ship_fee], [status], [updated_at], [coupon_coupon_id], [delivery_man_delivery_man_id], [store_store_id], [user_address_user_address_id], [user_profile_user_id], [discount_amount]) VALUES (N'52', N'105000', N'2023-05-17 09:48:20.8160000', N'2000', N'15000', N'success', N'2023-05-17 09:48:20.8160000', NULL, N'1', N'14', N'63', N'2', N'0')
GO

INSERT INTO [dbo].[user_order] ([order_id], [amount], [created_at], [service_fee], [ship_fee], [status], [updated_at], [coupon_coupon_id], [delivery_man_delivery_man_id], [store_store_id], [user_address_user_address_id], [user_profile_user_id], [discount_amount]) VALUES (N'53', N'105000', N'2023-05-17 09:48:21.2510000', N'2000', N'15000', N'success', N'2023-05-17 09:48:21.2510000', NULL, N'1', N'14', N'63', N'2', N'0')
GO

INSERT INTO [dbo].[user_order] ([order_id], [amount], [created_at], [service_fee], [ship_fee], [status], [updated_at], [coupon_coupon_id], [delivery_man_delivery_man_id], [store_store_id], [user_address_user_address_id], [user_profile_user_id], [discount_amount]) VALUES (N'54', N'45000', N'2023-05-17 09:57:47.9060000', N'2000', N'15000', N'success', N'2023-05-17 09:57:47.9060000', NULL, N'1', N'18', N'63', N'2', N'0')
GO

INSERT INTO [dbo].[user_order] ([order_id], [amount], [created_at], [service_fee], [ship_fee], [status], [updated_at], [coupon_coupon_id], [delivery_man_delivery_man_id], [store_store_id], [user_address_user_address_id], [user_profile_user_id], [discount_amount]) VALUES (N'55', N'332000', N'2023-05-17 10:57:32.8600000', N'2000', N'15000', N'user_canceled', N'2023-05-17 10:57:32.8600000', N'10', N'1', N'3', N'68', N'9', N'25000')
GO

INSERT INTO [dbo].[user_order] ([order_id], [amount], [created_at], [service_fee], [ship_fee], [status], [updated_at], [coupon_coupon_id], [delivery_man_delivery_man_id], [store_store_id], [user_address_user_address_id], [user_profile_user_id], [discount_amount]) VALUES (N'56', N'358199.999406934', N'2023-05-17 11:12:15.0130000', N'2000', N'15000', N'success', N'2023-05-17 11:12:15.0130000', N'17', N'1', N'3', N'72', N'6', N'39800.0005930662')
GO

INSERT INTO [dbo].[user_order] ([order_id], [amount], [created_at], [service_fee], [ship_fee], [status], [updated_at], [coupon_coupon_id], [delivery_man_delivery_man_id], [store_store_id], [user_address_user_address_id], [user_profile_user_id], [discount_amount]) VALUES (N'57', N'95400.0002145767', N'2023-05-14 11:13:20.3530000', N'2000', N'15000', N'success', N'2023-05-17 11:13:20.3530000', N'6', N'1', N'18', N'73', N'6', N'24599.9997854233')
GO

INSERT INTO [dbo].[user_order] ([order_id], [amount], [created_at], [service_fee], [ship_fee], [status], [updated_at], [coupon_coupon_id], [delivery_man_delivery_man_id], [store_store_id], [user_address_user_address_id], [user_profile_user_id], [discount_amount]) VALUES (N'58', N'398000', N'2023-05-15 11:59:30.1810000', N'2000', N'15000', N'success', N'2023-05-17 11:59:30.1810000', NULL, N'1', N'18', N'74', N'8', N'0')
GO

INSERT INTO [dbo].[user_order] ([order_id], [amount], [created_at], [service_fee], [ship_fee], [status], [updated_at], [coupon_coupon_id], [delivery_man_delivery_man_id], [store_store_id], [user_address_user_address_id], [user_profile_user_id], [discount_amount]) VALUES (N'59', N'40000', N'2023-05-16 14:30:27.5870000', N'2000', N'15000', N'user_canceled', N'2023-05-17 14:30:27.5870000', NULL, N'1', N'18', N'74', N'8', N'0')
GO

INSERT INTO [dbo].[user_order] ([order_id], [amount], [created_at], [service_fee], [ship_fee], [status], [updated_at], [coupon_coupon_id], [delivery_man_delivery_man_id], [store_store_id], [user_address_user_address_id], [user_profile_user_id], [discount_amount]) VALUES (N'60', N'105000', N'2023-05-17 14:33:12.6880000', N'2000', N'15000', N'success', N'2023-05-17 14:33:12.6880000', NULL, N'1', N'3', N'85', N'8', N'0')
GO

INSERT INTO [dbo].[user_order] ([order_id], [amount], [created_at], [service_fee], [ship_fee], [status], [updated_at], [coupon_coupon_id], [delivery_man_delivery_man_id], [store_store_id], [user_address_user_address_id], [user_profile_user_id], [discount_amount]) VALUES (N'61', N'48000', N'2023-05-17 14:43:14.1030000', N'2000', N'15000', N'success', N'2023-05-17 14:43:14.1030000', NULL, N'1', N'18', N'85', N'8', N'0')
GO

INSERT INTO [dbo].[user_order] ([order_id], [amount], [created_at], [service_fee], [ship_fee], [status], [updated_at], [coupon_coupon_id], [delivery_man_delivery_man_id], [store_store_id], [user_address_user_address_id], [user_profile_user_id], [discount_amount]) VALUES (N'62', N'100000', N'2023-05-17 14:43:51.2980000', N'2000', N'15000', N'success', N'2023-05-17 14:43:51.2980000', NULL, N'1', N'18', N'67', N'7', N'0')
GO

INSERT INTO [dbo].[user_order] ([order_id], [amount], [created_at], [service_fee], [ship_fee], [status], [updated_at], [coupon_coupon_id], [delivery_man_delivery_man_id], [store_store_id], [user_address_user_address_id], [user_profile_user_id], [discount_amount]) VALUES (N'63', N'255000', N'2023-05-17 14:45:49.1290000', N'2000', N'15000', N'success', N'2023-05-17 14:45:49.1290000', NULL, N'1', N'3', N'74', N'8', N'0')
GO

INSERT INTO [dbo].[user_order] ([order_id], [amount], [created_at], [service_fee], [ship_fee], [status], [updated_at], [coupon_coupon_id], [delivery_man_delivery_man_id], [store_store_id], [user_address_user_address_id], [user_profile_user_id], [discount_amount]) VALUES (N'64', N'105000', N'2023-05-17 16:52:57.1080000', N'2000', N'15000', N'user_canceled', N'2023-05-17 16:52:57.1080000', NULL, N'1', N'3', N'74', N'8', N'0')
GO

INSERT INTO [dbo].[user_order] ([order_id], [amount], [created_at], [service_fee], [ship_fee], [status], [updated_at], [coupon_coupon_id], [delivery_man_delivery_man_id], [store_store_id], [user_address_user_address_id], [user_profile_user_id], [discount_amount]) VALUES (N'65', N'150000', N'2023-05-17 23:35:17.7440000', N'2000', N'15000', N'user_canceled', N'2023-05-17 23:35:17.7440000', NULL, N'1', N'3', N'74', N'8', N'0')
GO

INSERT INTO [dbo].[user_order] ([order_id], [amount], [created_at], [service_fee], [ship_fee], [status], [updated_at], [coupon_coupon_id], [delivery_man_delivery_man_id], [store_store_id], [user_address_user_address_id], [user_profile_user_id], [discount_amount]) VALUES (N'66', N'150000', N'2023-05-17 23:36:32.7280000', N'2000', N'15000', N'user_canceled', N'2023-05-17 23:36:32.7280000', NULL, N'1', N'3', N'74', N'8', N'0')
GO

INSERT INTO [dbo].[user_order] ([order_id], [amount], [created_at], [service_fee], [ship_fee], [status], [updated_at], [coupon_coupon_id], [delivery_man_delivery_man_id], [store_store_id], [user_address_user_address_id], [user_profile_user_id], [discount_amount]) VALUES (N'67', N'63600.0001430511', N'2023-05-17 23:50:28.2980000', N'2000', N'15000', N'user_canceled', N'2023-05-17 23:50:28.2980000', N'6', N'1', N'18', N'73', N'6', N'16399.9998569489')
GO

INSERT INTO [dbo].[user_order] ([order_id], [amount], [created_at], [service_fee], [ship_fee], [status], [updated_at], [coupon_coupon_id], [delivery_man_delivery_man_id], [store_store_id], [user_address_user_address_id], [user_profile_user_id], [discount_amount]) VALUES (N'68', N'150000', N'2023-05-18 00:11:24.7990000', N'2000', N'15000', N'success', N'2023-05-18 00:11:24.7990000', NULL, N'1', N'3', N'74', N'8', N'0')
GO

INSERT INTO [dbo].[user_order] ([order_id], [amount], [created_at], [service_fee], [ship_fee], [status], [updated_at], [coupon_coupon_id], [delivery_man_delivery_man_id], [store_store_id], [user_address_user_address_id], [user_profile_user_id], [discount_amount]) VALUES (N'69', N'150000', N'2023-05-18 00:12:42.9780000', N'2000', N'15000', N'user_canceled', N'2023-05-18 00:12:42.9780000', NULL, N'1', N'3', N'74', N'8', N'0')
GO

INSERT INTO [dbo].[user_order] ([order_id], [amount], [created_at], [service_fee], [ship_fee], [status], [updated_at], [coupon_coupon_id], [delivery_man_delivery_man_id], [store_store_id], [user_address_user_address_id], [user_profile_user_id], [discount_amount]) VALUES (N'70', N'150000', N'2023-05-18 00:13:41.8760000', N'2000', N'15000', N'user_canceled', N'2023-05-18 00:13:41.8760000', NULL, N'1', N'3', N'74', N'8', N'0')
GO

INSERT INTO [dbo].[user_order] ([order_id], [amount], [created_at], [service_fee], [ship_fee], [status], [updated_at], [coupon_coupon_id], [delivery_man_delivery_man_id], [store_store_id], [user_address_user_address_id], [user_profile_user_id], [discount_amount]) VALUES (N'71', N'40000', N'2023-05-18 09:36:35.2280000', N'2000', N'15000', N'user_canceled', N'2023-05-18 09:36:35.2280000', NULL, N'1', N'18', N'74', N'8', N'0')
GO

INSERT INTO [dbo].[user_order] ([order_id], [amount], [created_at], [service_fee], [ship_fee], [status], [updated_at], [coupon_coupon_id], [delivery_man_delivery_man_id], [store_store_id], [user_address_user_address_id], [user_profile_user_id], [discount_amount]) VALUES (N'72', N'270000', N'2023-05-18 10:02:44.5490000', N'2000', N'15000', N'success', N'2023-05-18 10:02:44.5490000', N'5', N'1', N'18', N'72', N'6', N'50000')
GO

SET IDENTITY_INSERT [dbo].[user_order] OFF
GO


-- ----------------------------
-- Table structure for user_profile
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[user_profile]') AND type IN ('U'))
	DROP TABLE [dbo].[user_profile]
GO

CREATE TABLE [dbo].[user_profile] (
  [user_id] bigint  IDENTITY(1,1) NOT NULL,
  [balance] float(53)  NULL,
  [created_at] datetime2(7)  NULL,
  [fcm_token] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [full_name] nvarchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [is_verified] bit  NOT NULL,
  [phone] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [points] int  NOT NULL,
  [updated_at] datetime2(7)  NULL
)
GO

ALTER TABLE [dbo].[user_profile] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of user_profile
-- ----------------------------
SET IDENTITY_INSERT [dbo].[user_profile] ON
GO

INSERT INTO [dbo].[user_profile] ([user_id], [balance], [created_at], [fcm_token], [full_name], [is_verified], [phone], [points], [updated_at]) VALUES (N'1', N'900000000', N'2023-04-05 13:24:52.7220000', N'fSHdQaA_QESQdDvxPxR6kS:APA91bHq18FtPXTsxv0wnfLdBFGlSLPYb3z5YgGeLMB_CkHXAYSDUTn8gNxja_CIdxQYJFXWzuA8DFNOVvLIcHIv4162J9_3grLLyjY_oRZrru2di_MPyecf62CwUnbxDJrQvB12on3N', N'Nguyễn Ngọc Tuyết Vi', N'0', N'0123456788', N'0', N'2023-04-05 13:24:52.7220000')
GO

INSERT INTO [dbo].[user_profile] ([user_id], [balance], [created_at], [fcm_token], [full_name], [is_verified], [phone], [points], [updated_at]) VALUES (N'2', N'900000000', N'2023-04-05 13:25:09.0830000', N'fSHdQaA_QESQdDvxPxR6kS:APA91bHq18FtPXTsxv0wnfLdBFGlSLPYb3z5YgGeLMB_CkHXAYSDUTn8gNxja_CIdxQYJFXWzuA8DFNOVvLIcHIv4162J9_3grLLyjY_oRZrru2di_MPyecf62CwUnbxDJrQvB12on3N', N'Nguyễn Minh Tú', N'0', N'0987777777', N'0', N'2023-04-05 13:25:09.0830000')
GO

INSERT INTO [dbo].[user_profile] ([user_id], [balance], [created_at], [fcm_token], [full_name], [is_verified], [phone], [points], [updated_at]) VALUES (N'4', N'900000000', N'2023-04-07 00:04:14.7600000', N'fSHdQaA_QESQdDvxPxR6kS:APA91bHq18FtPXTsxv0wnfLdBFGlSLPYb3z5YgGeLMB_CkHXAYSDUTn8gNxja_CIdxQYJFXWzuA8DFNOVvLIcHIv4162J9_3grLLyjY_oRZrru2di_MPyecf62CwUnbxDJrQvB12on3N', N'Nguyễn Văn A', N'0', N'0359637895', N'0', N'2023-04-07 00:04:14.7600000')
GO

INSERT INTO [dbo].[user_profile] ([user_id], [balance], [created_at], [fcm_token], [full_name], [is_verified], [phone], [points], [updated_at]) VALUES (N'6', N'899793600.000143', N'2023-05-08 11:33:17.9610000', N'f8sbzy7UR_yB_NXwp_xAD0:APA91bFlNyO8LGtSdS-pGK_VKKcshhppdbIG31JjmEYBFQZbcSNnuuZA-LvQHwVQeVRvhoibwR7zGgJcUGvz2osmig7Zt1ynl7I3qzBe10RvumNidOULyTPCe-G0S_VztIwEMeWVOlU5', N'Nguyễn Quốc Bảo', N'0', N'0123456789', N'0', N'2023-05-08 11:33:17.9610000')
GO

INSERT INTO [dbo].[user_profile] ([user_id], [balance], [created_at], [fcm_token], [full_name], [is_verified], [phone], [points], [updated_at]) VALUES (N'7', N'900000000', N'2023-05-13 01:37:32.8150000', N'dR-xVTVeQKWvMq5MXx1uuu:APA91bHA6cXh8LIOcHZI5FSd0clinSwzfn9Fh6wwW0QywKQvs67hD172OiQVq6ML_WTC79yxRFYYwyjB0Ab5j9tete0UBeuMQRmd-7jC-1M5BAkV-rWVnNij9zTGGGdXVy79dej38F44', N'Nguyễn Ngọc Tuyết Vi', N'0', N'0372913432', N'0', N'2023-05-13 01:37:32.8150000')
GO

INSERT INTO [dbo].[user_profile] ([user_id], [balance], [created_at], [fcm_token], [full_name], [is_verified], [phone], [points], [updated_at]) VALUES (N'8', N'899833000', N'2023-05-17 10:16:49.6250000', N'fSHdQaA_QESQdDvxPxR6kS:APA91bHq18FtPXTsxv0wnfLdBFGlSLPYb3z5YgGeLMB_CkHXAYSDUTn8gNxja_CIdxQYJFXWzuA8DFNOVvLIcHIv4162J9_3grLLyjY_oRZrru2di_MPyecf62CwUnbxDJrQvB12on3N', N'Minh Tú', N'0', N'0653444999', N'0', N'2023-05-17 10:16:49.6250000')
GO

INSERT INTO [dbo].[user_profile] ([user_id], [balance], [created_at], [fcm_token], [full_name], [is_verified], [phone], [points], [updated_at]) VALUES (N'9', N'900349000', N'2023-05-17 10:18:21.6450000', N'f8sbzy7UR_yB_NXwp_xAD0:APA91bFlNyO8LGtSdS-pGK_VKKcshhppdbIG31JjmEYBFQZbcSNnuuZA-LvQHwVQeVRvhoibwR7zGgJcUGvz2osmig7Zt1ynl7I3qzBe10RvumNidOULyTPCe-G0S_VztIwEMeWVOlU5', N'Nguyễn Bảo', N'0', N'0987654321', N'0', N'2023-05-17 10:18:21.6450000')
GO

INSERT INTO [dbo].[user_profile] ([user_id], [balance], [created_at], [fcm_token], [full_name], [is_verified], [phone], [points], [updated_at]) VALUES (N'10', N'900000000', N'2023-05-17 17:32:08.1010000', NULL, N'Nguyễn Minh Tú', N'0', N'0123356784', N'0', N'2023-05-17 17:32:08.1010000')
GO

INSERT INTO [dbo].[user_profile] ([user_id], [balance], [created_at], [fcm_token], [full_name], [is_verified], [phone], [points], [updated_at]) VALUES (N'11', N'0', N'2023-05-18 09:57:17.2720000', N'f8sbzy7UR_yB_NXwp_xAD0:APA91bFlNyO8LGtSdS-pGK_VKKcshhppdbIG31JjmEYBFQZbcSNnuuZA-LvQHwVQeVRvhoibwR7zGgJcUGvz2osmig7Zt1ynl7I3qzBe10RvumNidOULyTPCe-G0S_VztIwEMeWVOlU5', N'Nguyễn Quốc Bảo', N'0', N'0987654311', N'0', N'2023-05-18 09:57:17.2720000')
GO

SET IDENTITY_INSERT [dbo].[user_profile] OFF
GO


-- ----------------------------
-- Auto increment value for coupon
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[coupon]', RESEED, 22)
GO


-- ----------------------------
-- Primary Key structure for table coupon
-- ----------------------------
ALTER TABLE [dbo].[coupon] ADD CONSTRAINT [PK__coupon__58CF6389E35F82BC] PRIMARY KEY CLUSTERED ([coupon_id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for delivery_man
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[delivery_man]', RESEED, 1)
GO


-- ----------------------------
-- Primary Key structure for table delivery_man
-- ----------------------------
ALTER TABLE [dbo].[delivery_man] ADD CONSTRAINT [PK__delivery__7048C1226F76148D] PRIMARY KEY CLUSTERED ([delivery_man_id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table delivery_man_account
-- ----------------------------
ALTER TABLE [dbo].[delivery_man_account] ADD CONSTRAINT [PK__delivery__AB6E6165AD2CF7FF] PRIMARY KEY CLUSTERED ([email])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for food
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[food]', RESEED, 119)
GO


-- ----------------------------
-- Primary Key structure for table food
-- ----------------------------
ALTER TABLE [dbo].[food] ADD CONSTRAINT [PK__food__2F4C4DD8AF73BBD1] PRIMARY KEY CLUSTERED ([food_id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for order_item
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[order_item]', RESEED, 125)
GO


-- ----------------------------
-- Primary Key structure for table order_item
-- ----------------------------
ALTER TABLE [dbo].[order_item] ADD CONSTRAINT [PK__order_it__3764B6BCA40AB98B] PRIMARY KEY CLUSTERED ([order_item_id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for review
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[review]', RESEED, 48)
GO


-- ----------------------------
-- Primary Key structure for table review
-- ----------------------------
ALTER TABLE [dbo].[review] ADD CONSTRAINT [PK__review__60883D90D3F288D7] PRIMARY KEY CLUSTERED ([review_id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for store
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[store]', RESEED, 30)
GO


-- ----------------------------
-- Primary Key structure for table store
-- ----------------------------
ALTER TABLE [dbo].[store] ADD CONSTRAINT [PK__store__A2F2A30C63121F39] PRIMARY KEY CLUSTERED ([store_id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table store_account
-- ----------------------------
ALTER TABLE [dbo].[store_account] ADD CONSTRAINT [PK__store_ac__AB6E61652D4A30AF] PRIMARY KEY CLUSTERED ([email])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table user_account
-- ----------------------------
ALTER TABLE [dbo].[user_account] ADD CONSTRAINT [PK__user_acc__AB6E61652C982DBD] PRIMARY KEY CLUSTERED ([email])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for user_address
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[user_address]', RESEED, 93)
GO


-- ----------------------------
-- Primary Key structure for table user_address
-- ----------------------------
ALTER TABLE [dbo].[user_address] ADD CONSTRAINT [PK__user_add__FEC0352E25F1E046] PRIMARY KEY CLUSTERED ([user_address_id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for user_favorites
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[user_favorites]', RESEED, 23)
GO


-- ----------------------------
-- Primary Key structure for table user_favorites
-- ----------------------------
ALTER TABLE [dbo].[user_favorites] ADD CONSTRAINT [PK__user_fav__6D2C5D4413303695] PRIMARY KEY CLUSTERED ([user_favorites_id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for user_order
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[user_order]', RESEED, 72)
GO


-- ----------------------------
-- Primary Key structure for table user_order
-- ----------------------------
ALTER TABLE [dbo].[user_order] ADD CONSTRAINT [PK__user_ord__465962296A1BB62F] PRIMARY KEY CLUSTERED ([order_id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for user_profile
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[user_profile]', RESEED, 11)
GO


-- ----------------------------
-- Primary Key structure for table user_profile
-- ----------------------------
ALTER TABLE [dbo].[user_profile] ADD CONSTRAINT [PK__user_pro__B9BE370F5D4927BE] PRIMARY KEY CLUSTERED ([user_id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Foreign Keys structure for table coupon
-- ----------------------------
ALTER TABLE [dbo].[coupon] ADD CONSTRAINT [FKaq0kkamqjwft8pyu5jl0y6vg2] FOREIGN KEY ([store_store_id]) REFERENCES [dbo].[store] ([store_id]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table delivery_man_account
-- ----------------------------
ALTER TABLE [dbo].[delivery_man_account] ADD CONSTRAINT [FK982fl5hyvwcj3u6o9l9h6lfdp] FOREIGN KEY ([delivery_man_id]) REFERENCES [dbo].[delivery_man] ([delivery_man_id]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table food
-- ----------------------------
ALTER TABLE [dbo].[food] ADD CONSTRAINT [FKb4k20i9qrq3sxe2yo5vwp9gw7] FOREIGN KEY ([store_store_id]) REFERENCES [dbo].[store] ([store_id]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table order_item
-- ----------------------------
ALTER TABLE [dbo].[order_item] ADD CONSTRAINT [FKadmap3epysrdcleb7vr32nf8r] FOREIGN KEY ([food_food_id]) REFERENCES [dbo].[food] ([food_id]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

ALTER TABLE [dbo].[order_item] ADD CONSTRAINT [FKpds63gbinljau7o4ieotcrm02] FOREIGN KEY ([order_order_id]) REFERENCES [dbo].[user_order] ([order_id]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table review
-- ----------------------------
ALTER TABLE [dbo].[review] ADD CONSTRAINT [FK82a20lwso6mj7k4vkbe5x3tqu] FOREIGN KEY ([order_id]) REFERENCES [dbo].[user_order] ([order_id]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

ALTER TABLE [dbo].[review] ADD CONSTRAINT [FKgsse37huttlksn709rejghi8i] FOREIGN KEY ([user_profile_user_id]) REFERENCES [dbo].[user_profile] ([user_id]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table store_account
-- ----------------------------
ALTER TABLE [dbo].[store_account] ADD CONSTRAINT [FKe47n23strhmjp1cgxirt9v07j] FOREIGN KEY ([store_id]) REFERENCES [dbo].[store] ([store_id]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table user_account
-- ----------------------------
ALTER TABLE [dbo].[user_account] ADD CONSTRAINT [FKtcjiyiw9ufmick8nifidqx1e5] FOREIGN KEY ([user_id]) REFERENCES [dbo].[user_profile] ([user_id]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table user_address
-- ----------------------------
ALTER TABLE [dbo].[user_address] ADD CONSTRAINT [FK3f8r1fpjxgewpvyhdlyh6gotv] FOREIGN KEY ([user_profile_user_id]) REFERENCES [dbo].[user_profile] ([user_id]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table user_favorites
-- ----------------------------
ALTER TABLE [dbo].[user_favorites] ADD CONSTRAINT [FKmd5rbnego572hx60xk9b3m9yp] FOREIGN KEY ([store_store_id]) REFERENCES [dbo].[store] ([store_id]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

ALTER TABLE [dbo].[user_favorites] ADD CONSTRAINT [FKsnoihococh3s6bdpirsefdwe] FOREIGN KEY ([user_profile_user_id]) REFERENCES [dbo].[user_profile] ([user_id]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table user_order
-- ----------------------------
ALTER TABLE [dbo].[user_order] ADD CONSTRAINT [FKndwxq760wnf7qmi01ioqfc4kx] FOREIGN KEY ([coupon_coupon_id]) REFERENCES [dbo].[coupon] ([coupon_id]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

ALTER TABLE [dbo].[user_order] ADD CONSTRAINT [FK8916ho73jo8d3auaucscnqqlk] FOREIGN KEY ([delivery_man_delivery_man_id]) REFERENCES [dbo].[delivery_man] ([delivery_man_id]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

ALTER TABLE [dbo].[user_order] ADD CONSTRAINT [FKlavn69wc6akg59yj9le1k2m3t] FOREIGN KEY ([store_store_id]) REFERENCES [dbo].[store] ([store_id]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

ALTER TABLE [dbo].[user_order] ADD CONSTRAINT [FK22emqjodvebnlxvcpcilselep] FOREIGN KEY ([user_address_user_address_id]) REFERENCES [dbo].[user_address] ([user_address_id]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

ALTER TABLE [dbo].[user_order] ADD CONSTRAINT [FKsus79y0iakgd1imd71srg8i2v] FOREIGN KEY ([user_profile_user_id]) REFERENCES [dbo].[user_profile] ([user_id]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

