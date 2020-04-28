package pl.lodz.p.it.webapplication.controllers;

import javax.annotation.sql.DataSourceDefinition;
import javax.enterprise.context.ApplicationScoped;

@DataSourceDefinition(name="java:global/TKS",
        className="org.postgresql.ds.PGConnectionPoolDataSource",
        url="jdbc:postgresql://ec2-46-137-177-160.eu-west-1.compute.amazonaws.com:5432/dfb6b8uta058g4",
        user="mzzfxlnwofocqy",
        password="4934643587f5dd6a9ce29d07b4ecd580524c084ca86a96ecfd21c2b947fd6652"
)
@ApplicationScoped
public class JdbcResourceBean {
}
