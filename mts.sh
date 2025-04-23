#!/bin/bash

# Configuration
# 你的mod id，确保和他人不重复且全小写
MOD_ID="template"
# 你的mod名
MOD_NAME="模板mod"
# 你的mod作者
MOD_AUTHOR="mod作者"
# 你的mod描述
MOD_DESC="mod描述描述描述。"
# 你的Steam路径（斜杆需要转义）
STEAM_PATH="C:\/Program Files (x86)\/Steam\/steamapps"
# 你的角色代号名（大写开头）
CHARACTER="Template"
# 你的角色代号名的全大写
CHARACTER_UP="TEMPLATE"
# 显示的角色名
CHARACTER_NAME="模板角色"

# 代码模板替换
find . -type f \( -name "*.java" -o -name "*.xml" -o -name "*.json" \) -exec sed -i "s/{{{mod_id}}}/$MOD_ID/g" {} \;
find . -type f \( -name "*.java" -o -name "*.xml" -o -name "*.json" \) -exec sed -i "s/{{{mod_name}}}/$MOD_NAME/g" {} \;
find . -type f \( -name "*.java" -o -name "*.xml" -o -name "*.json" \) -exec sed -i "s/{{{mod_author}}}/$MOD_AUTHOR/g" {} \;
find . -type f \( -name "*.java" -o -name "*.xml" -o -name "*.json" \) -exec sed -i "s/{{{mod_desc}}}/$MOD_DESC/g" {} \;
find . -type f \( -name "*.java" -o -name "*.xml" -o -name "*.json" \) -exec sed -i "s/{{{steam_path}}}/$STEAM_PATH/g" {} \;
find . -type f \( -name "*.java" -o -name "*.xml" -o -name "*.json" \) -exec sed -i "s/{{{character}}}/$CHARACTER/g" {} \;
find . -type f \( -name "*.java" -o -name "*.xml" -o -name "*.json" \) -exec sed -i "s/{{{character_up}}}/$CHARACTER_UP/g" {} \;
find . -type f \( -name "*.java" -o -name "*.xml" -o -name "*.json" \) -exec sed -i "s/{{{character_name}}}/$CHARACTER_NAME/g" {} \;

# 文件重命名
find . -type d \( -name "template_mod" -o -name "template_res" \) -exec sh -c 'MOD_ID="'$MOD_ID'"; mv "$0" "${0/template/$MOD_ID}"' {} \;
find . -type f \( -name "TemplateCharacter.java" -o -name "TemplateMod.java" -o -name "TemplateRelic.java" \) -exec sh -c 'CHARACTER="'$CHARACTER'"; mv "$0" "${0/Template/$CHARACTER}"' {} \;