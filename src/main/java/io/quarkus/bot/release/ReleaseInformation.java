package io.quarkus.bot.release;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class ReleaseInformation {

    private final String branch;
    private final String qualifier;
    private final boolean major;

    private String version;
    private boolean maintenance;

    @JsonCreator
    public ReleaseInformation(String version, String branch, String qualifier, boolean major, boolean maintenance) {
        this.version = version;
        this.branch = branch;
        this.qualifier = qualifier;
        this.major = major;
        this.maintenance = maintenance;
    }

    public String getVersion() {
        return version;
    }

    public String getBranch() {
        return branch;
    }

    public String getQualifier() {
        return qualifier;
    }

    public boolean isMaintenance() {
        return maintenance;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setMaintenance(boolean maintenance) {
        this.maintenance = maintenance;
    }

    @JsonIgnore
    public boolean isFinal() {
        return qualifier == null || qualifier.isBlank() || qualifier.equals("Final");
    }

    @JsonIgnore
    public boolean isFirstFinal() {
        if (version == null) {
            throw new IllegalStateException("Unable to know if the version is the first final at this stage");
        }

        return version.endsWith(".0") || version.endsWith(".0.Final");
    }

    @JsonIgnore
    public boolean isFirstCR() {
        return "CR1".equalsIgnoreCase(qualifier);
    }

    public boolean isMajor() {
        return major;
    }

    @Override
    public int hashCode() {
        return Objects.hash(branch, major, qualifier);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ReleaseInformation other = (ReleaseInformation) obj;
        return Objects.equals(version, this.version) && Objects.equals(branch, other.branch) && major == other.major
                && Objects.equals(qualifier, other.qualifier);
    }

    @Override
    public String toString() {
        return "ReleaseInformation [version=" + version + ", branch=" + branch + ", qualifier=" + qualifier + ", major=" + major + ",maintenance=" + maintenance
                + "]";
    }
}
