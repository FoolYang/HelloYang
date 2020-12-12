package com.example.annotation_compiler;

import com.example.annotation.BindPath;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;


@AutoService(Processor.class) // 把这个类标记为注解处理器
public class AnnotationProcessor extends AbstractProcessor {

    // 生成文件的对象
    Filer filer;
    // 打印日志对象
    Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
        messager = processingEnvironment.getMessager();

    }

    /**
     * 声明注解处理器支持的java版本
     * @return
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return processingEnv.getSourceVersion();
    }

    /**
     * 声明注解处理器要处理的注解
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new HashSet<>();
        types.add(BindPath.class.getCanonicalName());
        return types;
    }

    /**
     * 注解处理器的核心方法，在编译的时候会被调用
     * @param set
     * @param roundEnvironment
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        // 得到被BindPath标记的类节点
        Set<? extends Element> elementsAnnotatedWith = roundEnvironment.getElementsAnnotatedWith(BindPath.class);
        Map<String, String> map = new HashMap<>();
        // 类节点 TypeElement
        // 方法节点 ExecutableElement
        // 成员变量节点 VariableElement
        for (Element element : elementsAnnotatedWith) {
            // 能够获取到被注解标记的类对象以及注解里面的key
            TypeElement typeElement = (TypeElement) element;
            // 得到包名加类名
            String activityName = typeElement.getQualifiedName().toString();
            // 获取标记在这个类上面的注解
            String key = typeElement.getAnnotation(BindPath.class).value();
            map.put(key, activityName + ".class");
        }
        // 生成文件
        if (map.size() > 0) {
            // JavaPoet
//            Writer writer = null;
//            // 创建一个类名
//            String className = "ActivityUtil" + System.currentTimeMillis();
//            // 才去生成文件
//            try {
//                JavaFileObject sourceFile = filer.createSourceFile("com.example.util." + className);
//                writer = sourceFile.openWriter();
//                StringBuffer stringBuffer = new StringBuffer();
//                stringBuffer.append("package com.example.util;\n");
//                stringBuffer.append("import com.example.router.Arouter;\n"
//                        + "import com.example.router.IRouter;\n" +
//                        "\n" + "public class " + className + " implements IRouter { \n"
//                        + " @Override\n" + " public void putActivity() { \n");
//
//                Iterator<String> iterator = map.keySet().iterator();
//                while (iterator.hasNext()) {
//                    String key = iterator.next();
//                    String value = map.get(key);
//                    stringBuffer.append("  Arouter.getInstance().addActivity(\"" + key + "\", " + value + ");\n");
//                }
//                stringBuffer.append("\n }\n}");
//                writer.write(stringBuffer.toString());
//            } catch (Throwable tr) {
//                tr.printStackTrace();
//            } finally {
//                if (writer != null) {
//                    try {
//                        writer.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }

            // 使用javapoet生成java文件，替换上面手写过程
            createClass(map);
        }
        return false;
    }

    private void createClass(Map<String , String> map) {
        try {
            // 创建一个方法
            MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("putActivity")
                    .addModifiers(Modifier.PUBLIC)
                    .returns(void.class);
            Iterator<String> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                String activityName = map.get(key);
                methodBuilder.addStatement("com.example.router.Arouter.getInstance().addActivity(\"" + key + "\"," + activityName + ")");
            }
            MethodSpec methodSpec = methodBuilder.build();

            // 获取到接口的类
            ClassName iRouter = ClassName.get("com.example.router", "IRouter");
            // 创建工具类
            TypeSpec typeSpec = TypeSpec.classBuilder("ActivityUtil" + System.currentTimeMillis())
                    .addModifiers(Modifier.PUBLIC)
                    .addSuperinterface(iRouter)
                    .addMethod(methodSpec).build();

            // 构建目录对象
            JavaFile javaFile = JavaFile.builder("com.example.util", typeSpec).build();

            javaFile.writeTo(filer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
