/*
 * Copyright (c) 2007-2013 Concurrent, Inc. All Rights Reserved.
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

package cascading.lingual.catalog.ddl;

import java.util.Arrays;

/**
 *
 */
public class DDLTable
  {
  DDLAction ddlAction;
  String name;
  DDLColumn[] ddlColumns;

  public DDLTable( DDLAction ddlAction, String name )
    {
    this.ddlAction = ddlAction;
    this.name = name;
    }

  public DDLTable( DDLAction ddlAction, String name, DDLColumn[] ddlColumns )
    {
    this.ddlAction = ddlAction;
    this.name = name;
    this.ddlColumns = ddlColumns;
    }

  @Override
  public String toString()
    {
    final StringBuilder sb = new StringBuilder();
    sb.append( "Table" );
    sb.append( "{action=" ).append( ddlAction );
    sb.append( ", name='" ).append( name ).append( '\'' );
    sb.append( ", columns=" ).append( ddlColumns == null ? "null" : Arrays.asList( ddlColumns ).toString() );
    sb.append( '}' );
    return sb.toString();
    }
  }
