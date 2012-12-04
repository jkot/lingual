/*
 * Copyright (c) 2007-2012 Concurrent, Inc. All Rights Reserved.
 *
 * Project and contact information: http://www.cascading.org/
 *
 * This file is part of the Cascading project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cascading.lingual.jdbc;

import cascading.tuple.Fields;
import org.junit.Test;

public class SimpleSqlPlatformTest extends JDBCPlatformTestCase
  {
  @Test
  public void testSelect() throws Exception
    {
    assertTablesEqual( "emps-select", "select empno, name from sales.emps" );
    }

  @Test
  public void testSelectFilterOne() throws Exception
    {
    assertTablesEqual( "emps-filter-one", "select name from sales.emps where empno = 120" );
    }

  @Test
  public void testSelectFilterTwo() throws Exception
    {
    assertTablesEqual( "emps-filter-two", "select name from sales.emps where empno = 120 or deptno = 20" );
    }

  @Test
  public void testSelectFilterThree() throws Exception
    {
    assertTablesEqual( "emps-filter-three", "select name from sales.emps where (empno = 120 or empno = 130) and deptno = 20" );
    }

  @Test
  public void testSelectHaving() throws Exception
    {
    assertTablesEqual( "emps-having", "select age from sales.emps group by age having age > 30" );
    }

  @Test
  public void testSelectOrderBy() throws Exception
    {
    assertTablesEqual( "emps-select-ordered", "select empno, name from sales.emps order by name" );
    }

  @Test
  public void testSelectOrderByAscDesc() throws Exception
    {
    assertTablesEqual( "emps-select-ordered-asc-desc", "select empno, name from sales.emps order by empno asc, name desc" );
    }

  @Test
  public void testSelectDistinct() throws Exception
    {
    assertTablesEqual( "emps-distinct", "select distinct gender from sales.emps" );
    }

  @Test
  public void testUnion() throws Exception
    {
    assertTablesEqual( "depts-union", "select name from sales.depts union all select name from sales.depts order by name" );
    }

  @Test
  public void testInnerJoin() throws Exception
    {
    assertTablesEqual( "emps-depts-join-inner", "select * from sales.emps join sales.depts on emps.deptno = depts.deptno" );
    }

  @Test
  public void testCountAll() throws Exception
    {
    assertTablesEqual( "emps-count", "select count(*) from sales.emps" );
    }

  @Test
  public void testCountCity() throws Exception
    {
    assertTablesEqual( "emps-city-count", "select count(city) from sales.emps" );
    }

  @Test
  public void testCountDistinctCity() throws Exception
    {
    assertTablesEqual( "emps-city-count-distinct", "select count( distinct city ) from sales.emps" );
    }

  @Test
  public void testCountDistinctCityDistinctAge() throws Exception
    {
    assertTablesEqual( "emps-city-count-distinct-age-sum-distinct", "select count( distinct city ), sum( distinct age ) from sales.emps" );
    }

  @Test
  public void testSum() throws Exception
    {
    assertTablesEqual( "depts-sum", "select sum( deptno ) from sales.depts" );
    }

  @Test
  public void testMax() throws Exception
    {
    assertTablesEqual( "depts-max", "select max( deptno ) from sales.depts" );
    }

  @Test
  public void testMin() throws Exception
    {
    assertTablesEqual( "depts-min", "select min( deptno ) from sales.depts" );
    }

  @Test
  public void testAvg() throws Exception
    {
    assertTablesEqual( "depts-avg", "select avg( deptno ) from sales.depts" );
    }

  @Test
  public void testSumMaxMinAvg() throws Exception
    {
    assertTablesEqual( "depts-sum-max-min-avg", "select sum( deptno ), max( deptno ), min( deptno), avg( deptno ) from sales.depts" );
    }

  @Test
  public void testGroupByCount() throws Exception
    {
    assertTablesEqual( "emps-groupby-count", "select deptno, count(*) from sales.emps group by deptno" );
    }

  @Test
  public void testAnonGroupBySum() throws Exception
    {
    assertTablesEqual( "emps-anon-groupby-sum", "select sum(age) from sales.emps group by deptno" );
    }

  @Test
  public void testMultiGroupBy() throws Exception
    {
    assertTablesEqual( "emps-multi-groupby", "select deptno, gender, min(age), max(age) from sales.emps group by deptno, gender" );
    }

  @Test
  public void testSelectUnionOrderBy() throws Exception
    {
    assertTablesEqual( "emps-depts-union-groupby", "select * from (select name from sales.emps union select name from sales.depts) order by 1" );
    }

  @Test
  public void testIntoSelect() throws Exception
    {
    setResultsTo( "TEST", "RESULTS", new Fields( "EMPNO", "NAME" ).applyTypes( int.class, String.class ) );

    assertTablesEqual( "emps-select", "insert into test.results select empno, name from sales.emps" );
    }
  }