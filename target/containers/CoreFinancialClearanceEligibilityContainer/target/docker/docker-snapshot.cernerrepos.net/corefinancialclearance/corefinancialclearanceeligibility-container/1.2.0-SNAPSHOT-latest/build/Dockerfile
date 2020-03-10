FROM docker.cernerrepos.net/ibus-docker/openjdk8-alpine:1.0
ENV SCENARIO_NAME=CoreFinancialClearanceEligibilityContainer
COPY maven /
CMD ["/app/bin/spring_boot_app.sh","/corefinancialclearanceeligibility-container-1.2.0-SNAPSHOT.jar"]
