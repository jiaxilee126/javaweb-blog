Drop Table If Exists `Article`;
Create Table `Article`(
	`Id` Int Unsigned Not Null Auto_Increment Comment '主键',
	`Title` Varchar(50) Not Null Comment '文章名称',
	`Image` Varchar(50) Null Comment '文章封面图片',
	`Content` MediumText Not Null Comment '文章内容',
	`CreateTime` DateTime Null Comment '文章发布时间',
	Primary Key(`Id`)
)Comment = '文章';
