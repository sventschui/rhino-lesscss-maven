package ch.sventschui.lesscss.configuration;

/*
 * (C) Copyright 2014 Sven Tschui (http://eniu.ch/) and others.
 * 
 * All rights reserved. This program and the accompanying materials are made available under the terms of the GNU Lesser
 * General Public License (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-2.1.html
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

public class Options {
    private String includePath = null;

    private String depends = null;

    private Boolean noIeCompat = null;

    private Boolean noJs = null;

    private Boolean lintOnly = null;

    private Boolean strictImports = null;

    private Boolean insecure = null;

    private Boolean compress = null;

    private Boolean cleanCss = null;

    // TODO: full source-map support
    private Boolean sourceMap = null;

    private String rootpath = null;

    private Boolean relativeUrls = null;

    private Boolean strictMath = null;

    private Boolean strictUnits = null;

    private Variable[] globalVars = null;

    private Variable[] modifyVars = null;

    public Options() {
    }

    public Options(String includePath, String depends, Boolean noIeCompat, Boolean noJs, Boolean lintOnly,
            Boolean strictImports, Boolean insecure, Boolean compress, Boolean cleanCss, Boolean sourceMap,
            String rootpath, Boolean relativeUrls, Boolean strictMath, Boolean strictUnits, Variable[] globalVars,
            Variable[] modifyVars) {
        this();
        this.includePath = includePath;
        this.depends = depends;
        this.noIeCompat = noIeCompat;
        this.noJs = noJs;
        this.lintOnly = lintOnly;
        this.strictImports = strictImports;
        this.insecure = insecure;
        this.compress = compress;
        this.cleanCss = cleanCss;
        this.sourceMap = sourceMap;
        this.rootpath = rootpath;
        this.relativeUrls = relativeUrls;
        this.strictMath = strictMath;
        this.strictUnits = strictUnits;
        this.globalVars = globalVars;
        this.modifyVars = modifyVars;
    }

    public String getIncludePath() {
        return includePath;
    }

    public void setIncludePath(String includePath) {
        this.includePath = includePath;
    }

    public String getDepends() {
        return depends;
    }

    public void setDepends(String depends) {
        this.depends = depends;
    }

    public Boolean isNoIeCompat() {
        return noIeCompat;
    }

    public void setNoIeCompat(Boolean noIeCompat) {
        this.noIeCompat = noIeCompat;
    }

    public Boolean isNoJs() {
        return noJs;
    }

    public void setNoJs(Boolean noJs) {
        this.noJs = noJs;
    }

    public Boolean isLintOnly() {
        return lintOnly;
    }

    public void setLintOnly(Boolean lintOnly) {
        this.lintOnly = lintOnly;
    }

    public Boolean isStrictImports() {
        return strictImports;
    }

    public void setStrictImports(Boolean strictImports) {
        this.strictImports = strictImports;
    }

    public Boolean isInsecure() {
        return insecure;
    }

    public void setInsecure(Boolean insecure) {
        this.insecure = insecure;
    }

    public Boolean isCompress() {
        return compress;
    }

    public void setCompress(Boolean compress) {
        this.compress = compress;
    }

    public Boolean isCleanCss() {
        return cleanCss;
    }

    public void setCleanCss(Boolean cleanCss) {
        this.cleanCss = cleanCss;
    }

    public Boolean isSourceMap() {
        return sourceMap;
    }

    public void setSourceMap(Boolean sourceMap) {
        this.sourceMap = sourceMap;
    }

    public String getRootpath() {
        return rootpath;
    }

    public void setRootpath(String rootpath) {
        this.rootpath = rootpath;
    }

    public Boolean isRelativeUrls() {
        return relativeUrls;
    }

    public void setRelativeUrls(Boolean relativeUrls) {
        this.relativeUrls = relativeUrls;
    }

    public Boolean isStrictMath() {
        return strictMath;
    }

    public void setStrictMath(Boolean strictMath) {
        this.strictMath = strictMath;
    }

    public Boolean isStrictUnits() {
        return strictUnits;
    }

    public void setStrictUnits(Boolean strictUnits) {
        this.strictUnits = strictUnits;
    }

    public Variable[] getGlobalVars() {
        return globalVars;
    }

    public void setGlobalVars(Variable[] globalVars) {
        this.globalVars = globalVars;
    }

    public Variable[] getModifyVars() {
        return modifyVars;
    }

    public void setModifyVars(Variable[] modifyVars) {
        this.modifyVars = modifyVars;
    }
    
}
