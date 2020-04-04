#include <algorithm>
#include <iostream>
#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <cmath>
#include <vector>
#include <map>
#include <set>
using namespace std;
typedef long long ll;

struct Student{
    int id;
    char name[20];
};

Student students[100];

bool cmp(Student a,Student b)
{
    return a.id<b.id;
}
int main()
{
    students[0].id = 5; strcpy(students[0].name,"wanghui");
    students[1].id = 3; strcpy(students[1].name,"zhangsan");
    students[2].id = 2; strcpy(students[2].name,"lisi");
    students[3].id = 4; strcpy(students[3].name,"linyifeng");
    students[4].id = 1; strcpy(students[4].name,"houzhenjie");

    sort(students,students+5,cmp);
    for(int i=0;i<5;i++)
    {
        printf("%d:%s\n",students[i].id,students[i].name);
    }

    return 0;
}