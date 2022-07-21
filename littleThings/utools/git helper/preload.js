window.exports = {
    "git-helper": { // 注意：键对应的是 plugin.json 中的 features.code
       mode: "doc", // 文档模式
       args: {
          // 索引集合
          // indexes: require('./indexes.json')
          indexes:[
            {
                t: 'git img',
                d: '流程图',
                p: 'doc/figre.html' //页面, 只能是相对路径
            },
            {
               t: 'git init',
               d: '新建',
               p: 'doc/init.html'
               },
               {
               t: 'git config',
               d: '配置',
               p: 'doc/config.html'
               },
               {
               t: 'git status',
               d: '状态',
               p: 'doc/status.html'
               },
               {
               t: 'git add',
               d: '添加',
               p: 'doc/add.html'
               },
               {
               t: 'git commit',
               d: '提交',
               p: 'doc/commit.html'
               },
               {
               t: 'git pull',
               d: '拉起',
               p: 'doc/pull.html'
               },
               {
               t: 'git push',
               d: '推送',
               p: 'doc/push.html'
               },
               {
               t: 'git remote',
               d: '远程管理',
               p: 'doc/remote.html'
               },
               {
               t: 'git log',
               d: '信息',
               p: 'doc/log.html'
               },
               {
               t: 'git diff',
               d: '对比差异',
               p: 'doc/diff.html'
               },
               {
               t: 'git branch',
               d: '分支',
               p: 'doc/branch.html'
               },
               {
               t: 'git rm',
               d: '删除',
               p: 'doc/rm.html'
               },
               {
               t: 'git checkout',
               d: '检出',
               p: 'doc/checkout.html'
               },
               {
               t: 'git reset',
               d: '撤销',
               p: 'doc/reset.html'
               },
               {
               t: 'git grep',
               d: '查找',
               p: 'doc/grep.html'
               },
               {
               t: 'git merge',
               d: '合并',
               p: 'doc/merge.html'
               },
               {
               t: 'git tag',
               d: '标签',
               p: 'doc/tag.html'
               },
                
          ],
          // 子输入框为空时的占位符，默认为字符串"搜索"
          placeholder: "搜索"
       }
    }
 }