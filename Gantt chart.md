```mermaid
gantt
    title 酒店客房管理系统开发计划
    dateFormat  YYYY-MM-DD

    section 需求分析
    需求调研           :a1, 2026-05-01, 7d

    section 系统设计
    功能设计           :a2, after a1, 7d
    数据库设计         :a3, after a1, 7d

    section 功能开发
    用户登录模块       :a4, after a2, 7d
    客房管理模块       :a5, after a2, 7d
    入住退房模块       :a6, after a4, 7d
    统计报表模块       :a7, after a5, 7d

    section 测试与文档
    系统测试           :a8, after a6, 7d
    文档编写           :a9, after a8, 3d
```
