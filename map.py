# coding:utf-8
# 依赖 pip install -U androguard
#
# 生成代码拷贝到java文件

from androguard.core.androconf import load_api_specific_resource_module
permissionMap = load_api_specific_resource_module('api_permission_mappings')

fj = open("StrictMethodGroupList.java", "w", encoding="utf-8")
import time
# 格式化成2016-03-20 11:45:39形式
timeStr = time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())
# 文件
fj.write('''
package com.hua.testhook.method;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * ⣿⣿⣿⣿⣿⣿⢟⣡⣴⣶⣶⣦⣌⡛⠟⣋⣩⣬⣭⣭⡛⢿⣿⣿⣿⣿
 * ⣿⣿⣿⣿⠋⢰⣿⣿⠿⣛⣛⣙⣛⠻⢆⢻⣿⠿⠿⠿⣿⡄⠻⣿⣿⣿
 * ⣿⣿⣿⠃⢠⣿⣿⣶⣿⣿⡿⠿⢟⣛⣒⠐⠲⣶⡶⠿⠶⠶⠦⠄⠙⢿
 * ⣿⠋⣠⠄⣿⣿⣿⠟⡛⢅⣠⡵⡐⠲⣶⣶⣥⡠⣤⣵⠆⠄⠰⣦⣤⡀
 * ⠇⣰⣿⣼⣿⣿⣧⣤⡸ ⣿⡀⠂⠁⣸⣿⣿⣿⣿⣇⠄⠈⢀⣿⣿⠿
 * ⣰⣿⣿⣿⣿⣿⣿⣿⣷⣤⣈⣙⠶⢾⠭⢉⣁⣴⢯⣭⣵⣶⠾⠓⢀⣴
 * ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣉⣤⣴⣾⣿⣿⣦⣄⣤⣤⣄⠄⢿⣿
 * ⣿⣿⣿⣿⣿⣿⣿⣿⠿⠿⠿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣇⠈⢿
 * ⣿⣿⣿⣿⣿⣿⡟⣰⣞⣛⡒⢒⠤⠦⢬⣉⣉⣉⣉⣉⣉⣉⡥⠴⠂⢸
 * ⠻⣿⣿⣿⣿⣏⠻⢌⣉⣉⣩⣉⡛⣛⠒⠶⠶⠶⠶⠶⠶⠶⠶⠂⣸⣿
 * ⣥⣈⠙⡻⠿⠿⣷⣿⣿⣿⣿⣿⣿⣿⣿⣿⣾⣿⠿⠛⢉⣠⣶⣶⣿⣿
 * ⣿⣿⣿⣶⣬⣅⣒⣒⡂⠈⠭⠭⠭⠭⠭⢉⣁⣄⡀⢾⣿⣿⣿⣿⣿⣿
 *
 * 
 * 
''')
fj.write('* 通过脚本生成:'+timeStr)
fj.write('''
* @author hua
* @file MethodGroupList
*/
''')
fj.write('''
public class StrictMethodGroupList {

    public List<ClassMethodGroup> getClassGroupList(){
        HashMap<String,ClassMethodGroup> map = new HashMap<>();
        //-----------------------------------------------
''')

for k, v in permissionMap.items():
    datas = str(k).split(';')
    clazz = datas[0][1:].replace('/', '.')
    method = datas[1].split('(')[0].replace('-', '')
    out = "addMethod(map,\""+clazz+"\",\""+method+"\");"
    fj.write("\t\t"+out+"\n")

fj.write('''
 //-----------------------------------------------
        return new LinkedList<>(map.values());
    }

    private void addMethod(HashMap<String,ClassMethodGroup> map,String className,String methodName){
        if (!map.containsKey(className)){
            map.put(className,new ClassMethodGroup(className));
        }
        try{
            map.get(className).addMethod(methodName);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }
}
''')


print("---------------- DONE --------------------")
