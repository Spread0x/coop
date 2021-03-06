/*
 * Copyright 2020 Typelevel
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

ThisBuild / baseVersion := "1.0"

ThisBuild / organization := "org.typelevel"
ThisBuild / organizationName := "Typelevel"

ThisBuild / publishGithubUser := "djspiewak"
ThisBuild / publishFullName := "Daniel Spiewak"

ThisBuild / strictSemVer := false

ThisBuild / crossScalaVersions := Seq("0.26.0", "0.27.0-RC1", "2.12.11", "2.13.2")

ThisBuild / githubWorkflowJavaVersions := Seq("adopt@1.8", "adopt@11", "adopt@14", "graalvm@20.1.0")

Global / homepage := Some(url("https://github.com/typelevel/coop"))

Global / scmInfo := Some(
  ScmInfo(
    url("https://github.com/typelevel/coop"),
    "git@github.com:typelevel/coop.git"))

lazy val root = project.in(file(".")).aggregate(core.jvm, core.js)
  .settings(noPublishSettings)

lazy val core = crossProject(JSPlatform, JVMPlatform).in(file("core"))
  .settings(
    name := "coop",

    libraryDependencies ++= Seq(
      "org.typelevel" %%% "cats-free" % "2.2.0",
      "org.typelevel" %%% "cats-mtl"  % "1.0.0",

      "org.specs2" %%% "specs2-core" % "4.10.3" % Test),

    mimaPreviousArtifacts := {
      val old = mimaPreviousArtifacts.value
      if (isDotty.value) Set() else old
    })
  .settings(dottyLibrarySettings)
  .settings(dottyJsSettings(ThisBuild / crossScalaVersions))

