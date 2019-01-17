package groovy.closure

class AndroidDSL {
    static main(age) {

        def android = {
            compileSdkVersion 25
            buildToolsVersion "25.0.2"
            defaultConfig {
                minSdkVersion 15
                targetSdkVersion 25
                versionCode 1
                versionName "1.0"
            }

        }

        Android be = new Android()
        //委托
        android.delegate = be
        android.call()
        println be.toString()
//        打印结果
//        Android{cCompileSdkVersion=25, mBuildToolsVersion='25.0.2', mProductFlavor=ProductFlavor{mVersionCode=1, mVersionName='1.0', mMinSdkVersion=15, mTargetSdkVersion=25}}


    }

}

class Android {
    private int cCompileSdkVersion
    private String mBuildToolsVersion
    private ProductFlavor mProductFlavor

    Android() {
        mProductFlavor = new ProductFlavor()
    }

    def compileSdkVersion(int compileSdkVersion) {
        cCompileSdkVersion = compileSdkVersion
    }

    def buildToolsVersion(String buildToolsVersion) {
        mBuildToolsVersion = buildToolsVersion
    }
    //参数为闭包
    def defaultConfig(Closure<ProductFlavor> closure) {
        //讲ProductFlavor委托给closure
        closure.delegate = mProductFlavor
        //设置策略
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        //执行
        closure.call()
    }


    @Override
    public String toString() {
        return "Android{" +
                "cCompileSdkVersion=" + cCompileSdkVersion +
                ", mBuildToolsVersion='" + mBuildToolsVersion + '\'' +
                ", mProductFlavor=" + mProductFlavor +
                '}';
    }
}

class ProductFlavor {
    private int mVersionCode
    private String mVersionName
    private int mMinSdkVersion
    private int mTargetSdkVersion

    def versionCode(int versionCode) {
        mVersionCode = versionCode
    }

    def versionName(String versionName) {
        mVersionName = versionName
    }

    def minSdkVersion(int minSdkVersion) {
        mMinSdkVersion = minSdkVersion
    }


    def targetSdkVersion(int targetSdkVersion) {
        mTargetSdkVersion = targetSdkVersion
    }

    @Override
    String toString() {
        return "ProductFlavor{" +
                "mVersionCode=" + mVersionCode +
                ", mVersionName='" + mVersionName + '\'' +
                ", mMinSdkVersion=" + mMinSdkVersion +
                ", mTargetSdkVersion=" + mTargetSdkVersion +
                '}'
    }
}
