@Library('iBusCommands')
def build = new com.cerner.ibus.PipelineBuilder(this)
build
.artifactsToArchive("target/app.properties")
.enableSonarQubeScan(false)
.enableMavenSite(false)
.execute()

/*
if (env.BRANCH_NAME == 'master') {
  build job: '../Selenium/master', quietPeriod: 5, wait: false
  build job: '../Postman/master', quietPeriod: 5, wait: false
}
*/