#include <iostream>

using namespace std;

int main() {
    uint64_t mass, total = 0;
    auto fuel = [](uint64_t mass) -> uint64_t { return mass/3-2; };
    while(cin >> mass) {
        total += fuel(mass);
    }
    cout << total << endl;
}
